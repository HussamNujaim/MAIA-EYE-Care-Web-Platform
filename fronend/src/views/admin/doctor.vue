<template>
  <div class="tab-container">
    <el-tabs v-model="activeName" style="margin-top:15px;" type="border-card">
      <el-tab-pane v-for="item in tabMapOptions" :key="item.key" :label="item.label" :name="item.key">
        <keep-alive>
          <div>
                 <div id="deptId" v-if="activeName=='DP'">
                <Departments/>
    </div>
    <div id="drId" v-if="activeName=='DR'">
              <Doctor/>
    </div>
       
   </div>
        </keep-alive>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import Doctor from './components/doctor'
import Departments from './components/departments'
export default {
  name: 'AdminDoctor',
    components: { Departments , Doctor},
  data() {
    return {
      compnayData:[],
      tabMapOptions: [
        { label: 'Doctor', key: 'DR' },
        { label: 'Departments', key: 'DP' }
        
      ],
      activeName: 'DR',
      createdTimes: 0
    }
  },
  watch: {
    activeName(val) {
      this.$router.push(`${this.$route.path}?tab=${val}`)
   
    }
  },
  created() {
    // init the default selected tab
    const tab = this.$route.query.tab
    if (tab) {
      this.activeName = tab
    }
  },
  methods: {
    showCreatedTimes() {
      this.createdTimes = this.createdTimes + 1
    }
  }
}
</script>

<style scoped>
  .tab-container {
    margin: 30px;
  }

  .hideTab {
    visibility: hidden;
  }
 .showTab {
    visibility: visible;
  }
</style>
