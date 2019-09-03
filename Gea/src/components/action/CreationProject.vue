<template>

  <div id="wrapper">

    <HeaderBase/>

    <MenuNav/>

    <!--  page content init-->
    <div id="page-wrapper" style="min-height: 506px;">

      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header" >{{projectName}}</h1>
        </div>
        <!-- /.col-lg-12 -->
      </div>

      <!--  page form init-->

      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-body">
              <div class="row">
                <div class="col-lg-6">
                    <div class="form-group">
                      <label>Title</label>
                      <input class="form-control" type="text" placeholder="title">
                    </div>
                    <div class="form-group">
                      <label>Description</label>
                      <textarea class="form-control" rows="3" placeholder="description"></textarea>
                    </div>

                  <ProjectMember/>

                    <button type="submit" class="btn btn-primary">Save</button>
                </div>
                <!-- /.row (nested) -->
              </div>
              <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
          </div>
          <!-- /.col-lg-12 -->
        </div>
      </div>
      <!--  page form finish-->
    </div>
    <!--  page content finish-->


 </div>

</template>

<script>
    import HeaderBase from '@/components/layouts/HeaderBase'
    import MenuNav from '@/components/layouts/MenuNav'
    import ProjectMember from '@/components/action/ProjectMember'

    export default {
        name: "CreationProject",
        components: {
            HeaderBase,
            MenuNav,
            ProjectMember
        },
        data () {
            return {
                projectName: '',
                errors: [],
            }
        },
        mounted() {
            console.log("init mounted");
            console.log("user: "+   this.$session.get('username'));
            console.log("token: "+   this.$session.get('token'));
            console.log("status: "+   this.$session.get('status'));
            console.log("projectName: "+ this.$session.get('projectName'));
            this.projectName = this.$session.get('projectName');
        },
        methods:{


        }
    }
</script>

<style scoped>

</style>
