<template>
  <div id="wrapper">

    <!-- /#page-registration form  -->
    <div id="form-otp-validation">
      <div class="col-lg-12">
        <div class="panel panel-green">
          <div class="panel-heading">
            <h1 >Voyager Geo WebApp Dev</h1>
          </div>
          <div class="panel-body">
            <div class="row">
              <div class="col-lg-12">

                  <div class="form-group">
                    <label for="validationCode">Otp validation code</label>
                    <input class="form-control" id="validationCode" name="validationCode" type="password" v-model="otpValue.otpTextField" >
                  </div>
                  <input type="submit" class="btn btn-success" value="Validate" v-on:click="checkOtpValidation">
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
        name: "OtpValidation",
        data () {
            return {
                otpValue: {otpTextField:'', username: ''},
                errors:[]
            }
        },
        methods:{
            checkOtpValidation: function () {
                console.log('entering checkOtpValidation');
                this.errors.shift();
                console.log('error lenght :'+ this.errors.length);

                if (!this.otpValue.otpTextField.length) {
                    this.errors.push("Otp validation code is required.");
                }
                console.log('param '+ this.otpValue.otpTextField);
                this.otpValue.username = this.$session.get('username');
                if (this.errors.length > 0) {
                    console.log('has error');
                    for(let index in this.errors){
                        console.log('this error'+ this.errors[index]);
                        this.errorOutput(this.errors[index]);
                    }
                }else{
                    console.log('call send submit ');
                    console.log('call param user: '+ this.otpValue.username);
                    this.sendFormSubmit();
                }

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
                this.$http.post(this.API_URL+'/v1/api/validation/otp', this.otpValue).
                then(function (response) {
                    console.log("validation success");
                    if (response.status === 200) {
                        this.$router.push('/login');
                    }
                }, function (err) {
                    this.errorOutput(err.body.message);
                })
            }

        }
    }
</script>

<style scoped>
  #form-otp-validation{
    width: 50%;
    margin-left: auto;
    margin-right: auto;
  }

</style>
