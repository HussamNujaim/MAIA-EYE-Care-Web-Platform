<template>
  <main class="d-flex align-items-center min-vh-100 py-3 py-md-0">
    <div class="container">
      <div class="card login-card">
        <div class="row no-gutters">
        
          <div class="col-md-7">
            <div class="card-body">
              <div class="brand-wrapper">
                <img src="@/assets/images/Neusoft_logo.svg" alt="logo" class="logo">
              </div>
              <p class="login-card-description">Sign into your account</p>
              <el-form
                ref="loginForm"
                :model="loginForm"
                :rules="loginRules"
                class="login-form"
                autocomplete="on"
                label-position="left"
              >
                <el-form-item prop="username">
                  <el-input
                    ref="username"
                    v-model="loginForm.username"
                    placeholder="Username"
                    name="username"
                    type="text"
                    prefix-icon="el-icon-user"
                    tabindex="1"
                    autocomplete="on"
                  />
                </el-form-item>

                <el-tooltip
                  v-model="capsTooltip"
                  content="Caps lock is On"
                  placement="right"
                  manual
                >
                  <el-form-item prop="password">
                    <el-input
                      :key="passwordType"
                      ref="password"
                      v-model="loginForm.password"
                      :type="passwordType"
                      placeholder="Password"
                      name="password"
                      prefix-icon="el-icon-lock"
                      tabindex="2"
                      autocomplete="on"
                      @keyup.native="checkCapslock"
                      @blur="capsTooltip = false"
                      @keyup.enter.native="handleLogin"
                    />
                    <span class="show-pwd" @click="showPwd">
                      <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
                    </span>
                  </el-form-item>
                </el-tooltip>
                  <el-form-item>
                      <el-input
					style="float: left; width:11rem;"
					class="input"
           tabindex="2"
					v-model="checkCode"
					placeholder="Please enter the code."
					prefix-icon="el-icon-edit"
				></el-input>
        	<el-button
					style="width:6rem;height: 50px; margin-left:2px; margin-top:-5px; margin-bottom:-10px "
					type="danger"
					plain
					@click="createCode"
				>{{Code}}</el-button>
  </el-form-item> 
			


                <el-form-item >
                  <el-checkbox  style="margin-bottom:-20px" ref="username" v-model="loginForm.remember">Rememer ME</el-checkbox>
                </el-form-item>

                <el-button 
                  :loading="loading"
                  type="primary"
                  style="width:100%; margin-top:-10px"
                  class="btn btn-block login-btn mb-4"
                  @click.native.prevent="handleLogin"
                >Login</el-button>
              </el-form>
              
        	<el-link style="float:right; margin-top:0px; text-decoration:none"	
				type="danger"
				:underline="false"
      
				 @click="handleCreate"
				icon="el-icon-back"
				>Register</el-link>

              <p class="login-card-footer-text">
                <a href="#!" class="text-reset">Reset Password</a>
              </p>
            </div>
          </div>
            <div class="col-md-5">
            <img src="@/assets/images/left_image.jpg" alt="login" class="login-card-img">
          </div>
        </div>


      </div>

       <el-dialog :title="'Create new user'" :visible.sync="dialogFormVisible">
  <el-form
          :model="registerNewUser"
          ref="registerNewUser"
          label-width="150px"
          class="register-new-user login-form"
           
        >

				<el-input

				class="input"
				v-model="username"
				placeholder="Please enter the user name."
				prefix-icon="el-icon-user-solid"
				></el-input>
				<el-input

				class="input"
				v-model="nickname"
				placeholder="Please enter the nick name."
				prefix-icon="el-icon-user"
				></el-input>
				<el-input

				class="input"
				v-model="password"
				placeholder="Please enter your password."
				prefix-icon="el-icon-lock"
				show-password
				></el-input>
				<el-input
				class="input"
				v-model="passwordSure"
				placeholder="Please enter password again."
				prefix-icon="el-icon-lock"
				show-password
				></el-input>
				<el-input

				class="input"
				v-model="phone"
				placeholder="Please enter your phone."
				prefix-icon="el-icon-phone"
				></el-input>
				<el-input
				class="input"
				v-model="email"
				placeholder="Please enter your e-mail."
				prefix-icon="el-icon-message"
				></el-input>

        <div style="">
          <br />
        

   <el-upload
                style="min-width:300px"
                ref="upload"
                drag
                action="api/sys-user/uploadPicture"
                :name="fileName"
                :file-list="fileList"
                :on-success="handleSuccess"
                :auto-upload="false"
                list-type="picture"
                :limit="1"
              >  <i class="el-icon-upload"></i>
                <div class="el-upload__text">
                  Drag the file here, or
                  <em>Click to upload</em>
                </div>
                <div
                  class="el-upload__tip"
               
                >It is recommended to upload JPG/PNG files and not exceed 500kb</div>
              </el-upload>

       </div>
   <el-button
                  :loading="loading"
                  type="primary"
                  style="width:100%margin-bottom:30px"
                  class=" btn-block login-btn mb-4"
                  @click.native.prevent="submitForm"
                >Register</el-button>
                

       
  </el-form>
   </el-dialog>

    </div>
  </main>
</template>

<script>
import { validUsername } from '@/utils/validate'

export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error('Please enter the correct user name'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 3) {
        callback(new Error('The password can not be less than 3 digits'))
      } else {
        callback()
      }
    }
    return {
          Code:"",
            checkCode: "",
      dialogFormVisible : false,
      pageInfo: {
        offset: 0,
        limit: 20
      },
      loginForm: {
        username: null,
        password: null/*,
        remember: false*/
      },
      loginRules: {
        username: [
          { required: true, trigger: 'blur', validator: validateUsername }
        ],
        password: [
          { required: true, trigger: 'blur', validator: validatePassword }
        ]
      },
      passwordType: 'password',
      capsTooltip: false,
      loading: false,
      showDialog: false,
      redirect: undefined,
      otherQuery: {},
      isBlurred: true,
      //
        username:"",
            password:"",
         nickname: "",
            passwordSure: "",
            phone: "",
            email: "",
            role_id:3,
            imgUrl:"",
            fileName: "fileName",
            img: ''
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        //
        const query = route.query
        if (query) {
          this.redirect = query.redirect
          this.otherQuery = this.getOtherQuery(query)
        }
      },
      immediate: true
    }
  },
  created() {
         this.createCode();
    // window.addEventListener('storage', this.afterQRScan)
  },
  mounted() {
    if (this.loginForm.username === '') {
      this.$refs.username.focus()
    } else if (this.loginForm.password === '') {
      this.$refs.password.focus()
    }
  },
  destroyed() {
    // window.removeEventListener('storage', this.afterQRScan)
  },
  methods: {   

     handleCreate() {
       this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['registerNewUser'].clearValidate()
      })
    },
          createCode(){
               let code="";
               let codeLength=3;
               let random=new Array(
                    0,1,2,3,4,5,6,7,8,9,    "A",
                                            "B",
                                            "C",
                                            "D",
                                            "E",
                                            "F",
                                            "G",
                                            "H",
                                            "I",
                                            "J",
                                            "K",
                                            "L",
                                            "M",
                                            "N",
                                            "O",
                                            "P",
                                            "Q",
                                            "R",
                                            "S",
                                            "T",
                                            "U",
                                            "V",
                                            "W",
                                            "X",
                                            "Y",
                                            "Z"
               )
            for(let i=0;i<codeLength;i++){
                // [0,1)*36---->[0,36)  1.5--->1
                 let index= Math.floor(Math.random()*36);
                 code=code+random[index];
            }
            this.Code=code
                
        },

    checkCapslock(e) {
      const { key } = e
      this.capsTooltip = key && key.length === 1 && key >= 'A' && key <= 'Z'
    },
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
           if(this.checkCode==""){
                 this.$notify.error("please enter your verification code")
                 return ;
            }
            if(this.checkCode != this.Code){
                 this.$notify.error("please check your verification code,your code is wrong")
                return ;
            }
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true

          /* this code used to convert {JSON} into {x-www-form-urlencoded} 
          const params = Object.entries(this.loginForm)
            .map(
              ([key, value]) =>
                `${encodeURIComponent(key)}=${encodeURIComponent(value)}`
            ).join('&')
          /* ---------------------------------- */
          this.$store
            .dispatch('user/login', this.loginForm)
            .then(() => {
              window.sessionStorage.setItem('username',this.loginForm.username)
              this.$router.push({
                path: this.redirect || '/dashboard',
                query: this.otherQuery
              })
              this.loading = false
            })
            .catch(() => {
              this.loading = false
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    getOtherQuery(query) {
      return Object.keys(query).reduce((acc, cur) => {
        if (cur !== 'redirect') {
          acc[cur] = query[cur]
        }
        return acc
      }, {})
    },
           handleSuccess(res){
         this.imgUrl=res.obj
        

      this.axios.post("api/sys-user/register", {
        username: this.username,
        password:this.password,
        nickname:this.nickname,
        phone:this.phone,
        email:this.email,
        sysRole:[{id:"1",roleName:"patient"}],
        imgUrl:this.imgUrl
      }).then(res => {

			let resdata=res.data;

        if (resdata.code == 504) {
				
          this.$notify.error(resdata.code+resdata.msg);
        }
        if (resdata.code == 200) {
          this.dialogFormVisible = false;
          this.$notify.success(resdata.msg);
        }
      });
 
        },  submitForm() {

      //this.$refs["registerNewUser"].validate(valid => {
          this.$refs.upload.submit();
       
     // });
   }
  }
}
</script>

<style lang="scss" scoped>
@import "node_modules/bootstrap/scss/bootstrap";
@import "node_modules/bootstrap-vue/src/index.scss";
.brand-wrapper {
  margin-bottom: 19px;
}
.brand-wrapper .logo {
  height: 37px;
}
.container{
  max-width: 750px;
}

.container-fluid,
.container-lg,
.container-md,
.container-sm,
.container-xl{width:100%;padding-right:15px;padding-left:15px;margin-right:auto;margin-left:auto}
@media (min-width:410px){.container,.container-sm{max-width:410px}}
@media (min-width:576px){.container,.container-sm{max-width:490px}}
@media (min-width:768px){.container,.container-md,.container-sm{max-width:720px}}

.show-pwd {
  position: absolute;
  right: 10px;
  top: 2px;
  font-size: 16px;
  cursor: pointer;
  user-select: none;
}

.login-card {
  border: 0;
  border-top-left-radius: 70px;
  border-bottom-right-radius: 70px;
  box-shadow: 0 10px 30px 0 rgba(172, 168, 168, 0.43);
  overflow: hidden;
}

.login-card-img {
  border-radius: 0;
  position: absolute;
  width: 100%;
  height: 100%;
  -o-object-fit: cover;
  object-fit: cover;
}
.login-card .card-body {
  padding: 85px 60px 60px;
}

.login-card-description {
  font-size: 25px;
  color: #000;
  font-weight: normal;
  margin-bottom: 23px;
}
.login-card form {
  max-width: 326px;
}
.login-card .form-control {
  border: 1px solid #d5dae2;
  padding: 15px 25px;
  margin-bottom: 20px;
  min-height: 45px;
  font-size: 13px;
  line-height: 15;
  font-weight: normal;
}
.login-card .form-control::-webkit-input-placeholder {
  color: #919aa3;
}
.login-card .form-control::-moz-placeholder {
  color: #919aa3;
}
.login-card .form-control:-ms-input-placeholder {
  color: #919aa3;
}
.login-card .form-control::-ms-input-placeholder {
  color: #919aa3;
}
.login-card .form-control::placeholder {
  color: #919aa3;
}
.login-card .login-btn {
  padding: 13px 20px 12px;
  background-color: #4a9ff9;
  border-radius: 4px;
  font-size: 17px;
  font-weight: bold;
  line-height: 20px;
  color: #fff;
  margin-bottom: 24px;
}
.login-card .login-btn:hover {
  border: 1px solid #000;
  background-color: transparent;
  color: #000;
}
.login-card .forgot-password-link {
  font-size: 14px;
  color: #919aa3;
  margin-bottom: 12px;
}
.login-card-footer-text {
  font-size: 16px;
  color: #0d2366;
  margin-bottom: 10px;
}

.login-card-footer-nav a {
  font-size: 14px;
  color: #919aa3;
}
.register-new-user .input{
  padding:3px;
  width:45%
}
</style>
