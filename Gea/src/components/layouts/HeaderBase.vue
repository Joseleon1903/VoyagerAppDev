<template>
  <!-- 	menu de navegación 	 -->
  <div class="navbar navbar-default navbar-static-top" style="margin-bottom: 0">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse"
              data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
        <span class="icon-bar"></span> <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/home" >Voyager Geo WebApp Dev</a>
    </div>
    <!-- /.navbar-header -->

    <ul class="nav navbar-top-links navbar-right">

      <li class="dropdown"><a class="dropdown-toggle"
                              data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
        <i class="fa fa-caret-down"></i>
      </a>
        <ul class="dropdown-menu dropdown-user">
          <li><a href="/profile"><i class="fa fa-user fa-fw"></i> User Profile</a></li>
          <li class="divider"></li>
          <li><a href="/logout"><i class="fa fa-sign-out fa-fw"></i>Logout</a></li>
        </ul> <!-- /.dropdown-user --></li>
      <!-- /.dropdown -->
    </ul>
  </div>
  <!-- 	menu de navegación 	end -->

</template>

<script>
    export default {
        name: "HeaderBase"
    }
</script>

<style scoped>

</style>
