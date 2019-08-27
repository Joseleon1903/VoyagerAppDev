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
                <form role="form" action="#" method="post">

                  <div class="form-group">
                    <label for="validationCode">Otp validation code</label>
                    <input class="form-control" id="validationCode" name="validationCode" type="password" >
                  </div>
                  <button type="submit" class="btn btn-success">Validate</button>
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
        name: "OtpValidation"
    }
</script>

<style scoped>
  #form-otp-validation{
    width: 50%;
    margin-left: auto;
    margin-right: auto;
  }

</style>
