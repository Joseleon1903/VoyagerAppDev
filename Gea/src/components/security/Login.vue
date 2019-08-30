<template>
  <div class="container">
    <div class="row">
      <div class="col-md-4 col-md-offset-4">
        <div class="login-panel panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">Please Sign In</h3>
          </div>
          <div class="panel-body">
              <fieldset>
                <div class="form-group">
                  <input class="form-control" placeholder="E-mail"  type="text" name="username" v-model="loginData.username" autofocus />
                </div>
                <div class="form-group">
                  <input class="form-control" placeholder="Password" name="password" type="password" v-model="loginData.password" />
                </div>
                <div class="checkbox">
                  <label>
                    <input name="remember" type="checkbox" value="Remember Me">Remember Me
                  </label>
                </div>
                <!-- Change this to a button or input when using this as a form -->
                <input class="btn btn-lg btn-success btn-block" type="submit" v-on:click="checkForm" value="Login"/>

                <ul class="pager">
                  <li class="next">
                    <p>If you are not registered <router-link to="/user/registration">Register</router-link></p>
                  </li>
                </ul>
              </fieldset>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
        loginData: {username: '', password: ''},
        errors: [],
    }
  },
  methods:{
      checkForm: function () {
          console.log("entering checkform ");
          this.errors= [];
          console.log("error lenght "+ this.errors.length);
          if (!this.loginData.username.length) {
              this.errors.push("username required.");
          }
          if (!this.loginData.password.length) {
              this.errors.push("password required.");
          }
          if (this.errors.length) {
              console.log('has error');
              for(let index in this.errors){
                  console.log('this error'+ this.errors[index]);
                  this.$parent.errorNotify(this.errors[index]);
              }
          }else{
              this.sendLoginSubmit();
          }

      },
      sendLoginSubmit: function () {
          console.log("entering send login request");
          this.$http.post(this.API_URL+'/api/auth/login', this.loginData).
          then(function (response) {
              console.log("registration success");
              if (response.status === 200) {
                  this.$session.set('username', response.body.username);
                  this.$session.set('status', response.body.status);
                  this.$session.set('token',response.body.token);
                  this.$router.push('/home');
              }
          }, function (err) {
              this.$parent.errorNotify(err.body.message);
          })

      }


  }



}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
