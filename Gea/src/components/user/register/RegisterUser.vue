<template>

  <div id="wrapper">

    <!-- /#page-registration form  -->
    <div id="form-registration" @submit="checkForm" method="post" novalidate="true">
      <div class="col-lg-12">
        <div class="panel panel-green">
          <div class="panel-heading">
            <h1>Voyager Geo</h1>
          </div>
          <div class="panel-body">
            <div class="row">
              <div class="col-lg-12">
                <form role="form" action="#"  method="post">

                  <div class="form-group">
                    <label for="firstName">First name</label>
                    <input class="form-control" id="firstName" name="firstName" type="text" v-model="formData.firstName" >
                  </div>

                  <div class="form-group">
                    <label for="lastName">Last name</label>
                    <input class="form-control" id="lastName" name="lastName" type="text" v-model="formData.lastName" >
                  </div>

                  <div class="form-group">
                    <label for="email">Email</label>
                    <input class="form-control" id="email" name="email" type="text" v-model="formData.email">
                  </div>

                  <div class="panel panel-default" >
                    <div class="panel-heading" style=" height: 35px;">
                      <h4 class="panel-title">
                        <p>Confirmation password</p>
                      </h4>
                    </div>
                  </div>

                  <div class="form-group">
                    <label for="password">Password</label>
                    <input class="form-control" type="password" id="password" name="password" v-model="formData.password">
                  </div>

                  <div class="form-group">
                    <label for="confirmPassword">Confirm password</label>
                    <input class="form-control" type="password" id="confirmPassword" name="confirmPassword" v-model="formData.confirmPassword">
                  </div>
                  <input type="submit" class="btn btn-success" value="Subscribe">
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
export default {
        name: 'RegisterUser',
        data () {
            return {
                formData:{ firstName: '', lastName: '', email: '' , confirmPassword: '', password: '' },
                errors: [],
            }
        },
        methods:{
            checkForm: function (e) {
                this.errors = [];
                e.preventDefault();
                console.log('checkForm');

                if (!this.formData.firstName.length) {
                    this.errors.push("firstName required.");
                }
                if (!this.formData.lastName) {
                    this.errors.push("lastName required.");
                }
                if (!this.formData.confirmPassword) {
                    this.errors.push("confirmPassword required.");
                }
                if (!this.formData.password) {
                    this.errors.push("password required.");
                }
                if (!this.formData.email) {
                    this.errors.push('Email required.');
                } else if (!this.validEmail(this.formData.email)) {
                    this.errors.push('Valid email required.');
                }

                if (this.errors.length) {
                    console.log('has error');
                    for(let index in this.errors){
                      console.log('this error'+ this.errors[index]);
                      this.errorOutput(this.errors[index]);
                    }
                }else{
                    this.sendFormSubmit();
                }
            },
            validEmail: function (email) {
                let re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                return re.test(email);
            },
            errorOutput: function (message) {
                this.$notify({
                    group: 'foo',
                    type:'error',
                    title: 'invalid input',
                    text: message
                });
            },
            sendFormSubmit: function () {
                console.log("entering sendFormSubmit");
                this.$http.post(this.API_URL+'/v1/api/registration', this.formData).
                then(function (response) {
                    console.log("registration success");
                    if (response.status === 200) {
                        this.$session.set('username', response.body.username);
                        this.$router.push('/user/registration/validation/otp');
                    }
                }, function (err) {
                    this.errorOutput(err.body.message);
                })

            }
        }
    }
</script>

<style scoped>

  #form-registration{
    width: 50%;
    margin-left: auto;
    margin-right: auto;
  }
  .error{
    border-color: #a94442;
    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
  }

</style>
