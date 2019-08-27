<template>

  <div id="wrapper">

    <!-- /#page-registration form  -->
    <div id="form-registration">
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
                    <input class="form-control" id="firstName" name="firstName" type="text" >
                  </div>

                  <div class="form-group">
                    <label for="lastName">Last name</label>
                    <input class="form-control" id="lastName" name="lastName" type="text" >
                  </div>

                  <div class="form-group">
                    <label for="email">Email</label>
                    <input class="form-control" id="email" name="email" type="email" >
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
                    <input class="form-control" type="password" id="password" name="password" >
                  </div>

                  <div class="form-group">
                    <label for="confirmPassword">Confirm password</label>
                    <input class="form-control" type="password" id="confirmPassword" name="confirmPassword" >
                  </div>
                  <button type="submit" class="btn btn-success">Subscribe</button>
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
        name: "RegisterUser"
    }
</script>

<style scoped>

  #form-registration{
    width: 50%;
    margin-left: auto;
    margin-right: auto;
  }

</style>
