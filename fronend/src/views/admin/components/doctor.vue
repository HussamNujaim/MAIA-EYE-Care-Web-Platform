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
        <el-button  type="primary" @click="handleCreate" icon="el-icon-plus">New Doctor</el-button>
    </div>
    <div class="cont">
      <el-table
      :data="tableData"
      style="width: 100%;"
      class="table-check"
    >
      <el-table-column prop="id" label="Doctor Id"></el-table-column>
      <el-table-column prop="doctorName" label="Doctor Name"></el-table-column>
      <el-table-column prop="address" label="Address"></el-table-column>
      <el-table-column prop="age" label="Age"></el-table-column>
      <el-table-column prop="gender" label="Gender"></el-table-column>
      <el-table-column prop="dept.deptName" label="Department"></el-table-column>

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
          class="add-goods-form"
           
        >
     
          <el-form-item
            label="Doctor Name"
            prop="doctorName"
            :rules="[{ required: true, message: 'Please enter the Doctor Name'}]"
          >
            <el-input style="width:25rem" v-model="addForm.doctorName" autocomplete="off"></el-input>
          </el-form-item>
          <br />
          <el-form-item
            label="Age"
            prop="age"
            :rules="[{ required: true, message: 'Please enter the Age'}]"
          >
            <el-input style="width:25rem" v-model="addForm.age" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item
            label="Address"
            prop="address"
            :rules="[{ required: true, message: 'Please enter the Address'}]"
          >
            <el-input style="width:25rem" v-model="addForm.address" autocomplete="off"></el-input>
          </el-form-item>
        <el-form-item label="Department" prop="depts_list">
<el-select
              style="width:25rem"
              placeholder
              v-model="addForm.dept.id"
              autocomplete="off"
              ref="selectCh"
            >
              <el-option
                v-for="item in depts_list"
                :key="item.id"
                :label="item.deptName"
                :value="item.id"
              ></el-option>
            </el-select>
            
                      </el-form-item>
                      <el-form-item label="Gender" >

       <div class="radio" style="width: 45%;  margin-left:10px;margin-top:5px;">
				<el-radio v-model="addForm.gender" label="Male">Male</el-radio>
				<el-radio v-model="addForm.gender" label="Female">Female</el-radio>
      </div>
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
  name:'Doctor',
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
      urlCreate:'/api/doctor/insert',
      urlUpdate:'/api/doctor/update',
      urlDelete:'/api/doctor/delete',
      urlGet:'/api/doctor/get',
      tableData:[],
    search:"",
    pagesize:5,
    currentPage:1,
      loading: false,
        depts_list:null,
        addForm: {
             id: null,
              doctorName:null,
              age:null,
              address:null,
              gender:null,
              dept: {id:null,deptName:null},
              salary: null
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
        this.getDeptList()

    this.getList()
  },
  methods: {
    checkPermission,
        resetaddForm() {
      this.addForm = {
               id: null,
              doctorName:null,
              age:null,
              gender:null,
              address:null,
              dept: {id:null,deptName:null},
              salary: null
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
              doctorName:this.addForm.doctorName,
              age:this.addForm.age,
              gender:this.addForm.gender,
              address:this.addForm.address,
              dept: {id:this.addForm.dept.id,deptName:this.$refs.selectCh.selected.label},
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
      if (msg == "Not in warehouse") return "danger";
      if (msg == "In warehouse") return "success";
      if (msg == "On shelf") return "warning";
    },
    btn(msg) {
      if (msg == "Not in warehouse") return "Push";
      if (msg == "In warehouse") return "Shelve";
      if (msg == "On shelf") return "Unshelve";
    },
    tag(msg) {
      if (msg == "Not in warehouse") return "danger";
      if (msg == "In warehouse") return "warning";
      if (msg == "On shelf") return "success";
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
       getDeptList() {
      this.loading = true
      let listQuery = {currentPage: 1,
			pageSize: 100}
      getData("/api/dr-department/get", listQuery).then(response => {
                console.log("response")

        console.log(response)
      let resdata=response;
                if(resdata.code==200){
              this.depts_list =resdata.obj
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