<template>
  <div class="container">
    <div class="row">
      <div class="col-md-4 col-md-offset-4">
        <div class="login-panel panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">Please Sign In</h3>
          </div>
          <div class="panel-body">
            <form role="form" action="#"  method="POST">
              <fieldset>
                <div class="form-group">
                  <input class="form-control" placeholder="E-mail"  type="text" name="username" autofocus />
                </div>
                <div class="form-group">
                  <input class="form-control" placeholder="Password" name="password" type="password" />
                </div>
                <div class="checkbox">
                  <label>
                    <input name="remember" type="checkbox" value="Remember Me">Remember Me
                  </label>
                </div>
                <!-- Change this to a button or input when using this as a form -->
                <input class="btn btn-lg btn-success btn-block" type="submit" value="Login"/>

                <ul class="pager">
                  <li class="next">
                    <p>If you are not registered <router-link to="/user/registration">Register</router-link></p>
                  </li>
                </ul>

              </fieldset>
            </form>
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
      msg: 'Welcome to Your Vue.js App'
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
