<template>
<div class="app-container">
       <div class="filter-container">
        <span>
          Search：
          <el-input
            style="width:300px"
            placeholder="Doctor Name"
            @input="fsearch"
            v-model="search"
          ></el-input>
        </span>
        <el-button type="primary" plain  icon="el-icon-search"></el-button>
        <el-button  type="primary" @click="handleCreate" icon="el-icon-plus">New Appointment</el-button>
    </div>
    <div class="cont">
      <el-table
      :data="tableData"
      style="width: 100%;"
      class="table-check"
    >
      <el-table-column prop="id" label="Appointment Id"></el-table-column>
      <el-table-column prop="appointmentTime" label="Appointment Time"></el-table-column>
      <el-table-column prop="doctor.doctorName" label="Doctor"></el-table-column>


<el-table-column
        prop="tag"
        label="tag"
        :filter-method="filterHandler"
        :filters="[{ text: 'available', value: 'available' },{ text: 'booked', value: 'booked' }, { text: 'diagnosed', value: 'diagnosed'}]"
      >
        <template #default="scope">
          <el-tag :type="tag(scope.row.tag)" disable-transitions>{{scope.row.tag}}</el-tag>
        </template>
      </el-table-column>


      <el-table-column label="operation" width="250">
        <template  #default="scope">
          <el-button type="success" size="small" icon="el-icon-edit" @click="handleUpdate(scope.row)"></el-button>
          <el-button
            type="danger"
            size="small"
            icon="el-icon-delete"
            @click="handleDelete(scope.row, scope.$index)"
          ></el-button>
        </template>
      </el-table-column>
    </el-table>

   <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5,10,20,total]"
          :page-size="pagesize"
          layout="total,sizes,prev,pager,next,jumper"
          :total="total"
        > </el-pagination>  


            <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
        <el-form
          :model="addForm"
          ref="addForm"
          label-width="150px"
          class="add-form"
           
        >
     
          <el-form-item
            label="Appointment Time"
            prop="appointmentTime"
            :rules="[{ required: true, message: 'Please enter the Time'}]"
          >
              <el-date-picker 
      style="width: 80%;"
      v-model="addForm.appointmentTime"
      type="datetime"
      placeholder="Select the appointment date and time"
      :picker-options="pickerOptions"
      name="start_at"
      v-validate="'required|date_format:YYYY-MM-DD HH:mm'"
      format="dd/MM/yyyy HH:mm"
    >
    </el-date-picker>  
          </el-form-item>
          <br />
        <el-form-item label="Doctor" prop="doctors_list" >
<el-select
              style="width:25rem"
              placeholder
              v-model="addForm.doctor.id"
              autocomplete="off"
              ref="selectCh"
            >
              <el-option
                v-for="item in doctors_list"
                :key="item.id"
                :label="item.doctorName"
                :value="item.id"
              ></el-option>
            </el-select>
            
                      </el-form-item>
                      
        </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          Cancel
        </el-button>
        <el-button type="primary" @click="insertData('addForm')">
          Confirm
        </el-button>
      </div>
    </el-dialog>


        </div>  </div> 
</template>

<script>
import permission from '@/directive/permission/index'
import checkPermission from '@/utils/permission'
import {getUserName, getUser} from '@/api/getsession'

//import {deleteData, updateData, createData, getData } from '@/api/MainAPI'
import {deleteData, updateData, createData, getData } from '@/api/MainAPI'
export default {
  name:'Appointment',
    directives: { permission },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'info',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  props: {
    type: {
      type: String,
      default: 'CN'
    }
  },
  data() {
    return {
      urlCreate:'/api/sys-appointment/insert',
      urlUpdate:'/api/sys-appointment/update',
      urlDelete:'/api/sys-appointment/delete',
      urlGet:'/api/sys-appointment/get',
      tableData:[],
    search:"",
    pagesize:5,
    currentPage:1,
      loading: false,
        doctors_list:null,
        addForm: {
             id: null,
              appointmentTime:null,
              doctor: {id:null,doctorName:null},
              notes: null,
              tag:null
      },
      dialogFormVisible: false,
      dialogStatus: '',
       textMap: {
        update: 'Edit',
        create: 'Create',
           rules: {
        type: [{ required: true, message: 'type is required', trigger: 'change' }],
        timestamp: [{ type: 'date', required: true, message: 'timestamp is required', trigger: 'change' }],
        title: [{ required: true, message: 'title is required', trigger: 'blur' }]
      }
      }
    }
  },
  created() {
        this.getDoctorsList()

    this.getList()
  },
  methods: {
    checkPermission,
        resetaddForm() {
      this.addForm = {
            id: null,
              appointmentTime:null,
              doctor: {id:null,doctorName:null},
              notes: null,
              tag:null
              }
    },
      handleDelete(row, index) {
        this.addForm.id=row.id
       deleteData(this.urlDelete,this.addForm).then(newresponse => {
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Deleted Successfully',
              type: 'success',
              duration: 2000
            })
                           this.getList()
            this.dialogFormVisible=false
// this.$router.go(0);
          })

    },
       handleCreate() {
      this.resetaddForm()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['addForm'].clearValidate()
      })
    },
        insertData(res) {
          let formData= {
              appointmentTime:this.addForm.appointmentTime,
              doctor:this.addForm.age,
              notes:this.addForm.notes,
              tag:"available",
              doctor: {id:this.addForm.doctor.id,doctorName:this.$refs.selectCh.selected.label},
              salary: this.addForm.salary
          }
      this.$refs['addForm'].validate((valid) => {
        if (valid) {
          if(this.dialogStatus==='create')
          {
            
            getUser({username:sessionStorage.getItem("username")}).then(res3=>
            {
             console.log(formData)
               createData(this.urlCreate, formData).then(newresponse => {
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Created Successfully',
              type: 'success',
              duration: 2000
            })
            ///  this.$router.go(0);
            this.getList()
            this.dialogFormVisible=false
})
             
             
             })
            
          
          }
          else{
    updateData(this.urlUpdate, this.addForm).then(newresponse => {
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Updated Successfully',
              type: 'success',
              duration: 2000
            })
                        this.getList()
            this.dialogFormVisible=false

//              this.$router.go(0);
          })
          }

     
        }
      })
    },
       handleSizeChange(val) {
        console.log(`${val} items per page`);
         this.pagesize = val;
         this.getList();
      },
      handleCurrentChange(val) {
        console.log(`current page: ${val}`);
         this.currentPage = val;
         this.getList();
      },
         handleUpdate(row) {
      this.addForm = Object.assign({}, row) // copy obj
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['addForm'].clearValidate()
      })
    },
  filterHandler(value, row, column) {
      const property = column["property"];
      return row[property] === value;
    },
  fsearch(val){
    console.log(val)
    this.search=val;
    this.tableData = this.table.filter(e =>
        e.title.match(this.search)
      );
  },
   btntag(msg) {
      if (msg == "available") return "danger";
      if (msg == "diagnosed") return "success";
      if (msg == "booked") return "primary";
    },
    btn(msg) {
      if (msg == "available") return "Book";
      if (msg == "diagnosed") return "Cancel";
      if (msg == "booked") return "Info";
    },
    tag(msg) {
      if (msg == "available") return "success";
      if (msg == "diagnosed") return "primary";
      if (msg == "booked") return "warning";
    },

    getList() {
      this.loading = true
      this.$emit('create') // for test
      let listQuery = {currentPage: this.currentPage,
							 pageSize: this.pagesize}
      getData(this.urlGet, listQuery).then(response => {
        console.log(response)
      let resdata=response;
                if(resdata.code==200){
                    console.log()
              this.tableData =resdata.obj
               this.total=resdata.totalRecords   
              this.loading = false
      }else{  
        this.$notify.warning(res.message);
      }

      })
    },
       getDoctorsList() {
      this.loading = true
      let listQuery = {currentPage: 1,
			pageSize: 100}
      getData("/api/doctor/get", listQuery).then(response => {
                console.log("response")

        console.log(response)
      let resdata=response;
                if(resdata.code==200){
              this.doctors_list =resdata.obj
               console.log(dept_list)
              this.loading = false
      }else{  
        this.$notify.warning(res.message);
      }

      })
    }
  }
}
</script>

<style scoped>
.cont{
  padding: 5px;
  background-color: rgb(245, 245, 245);
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
}
.maincont{
  padding: 5px;
}
</style>