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
                      <input class="form-control" type="text" placeholder="title" v-model="projectBody.title">
                    </div>
                    <div class="form-group">
                      <label>Description</label>
                      <textarea class="form-control" rows="3" placeholder="description" v-model="projectBody.description"></textarea>
                    </div>

                  <ProjectMember ref="projectMember"/>

                    <button type="submit" class="btn btn-primary" v-on:click="checkForm">Save</button>
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
                projectBody: {title: '', description: ''},
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

            checkForm: function () {
                this.errors= [];
                console.log("error lenght "+ this.errors.length);
                if (!this.projectBody.title.length) {
                    this.errors.push("title of project required.");
                }
                if (!this.projectBody.description.length) {
                    this.errors.push("password required.");
                }
                if (this.errors.length) {
                    console.log('has error');
                    for(let index in this.errors){
                        console.log('this error'+ this.errors[index]);
                        this.$parent.errorNotify(this.errors[index]);
                    }
                }else{
                    this.sendPost();
                }

            },
            sendPost: function () {
                console.log('entering sendPost');
                let nameProject = this.$session.get('projectName');
                let membersStr = [];
                let members = this.$refs.projectMember.projectMember;
                for(let index in members){
                    console.log('entering for members');
                    membersStr.push(members[index].name);
                }

                let bodyRequest= {projectName: nameProject, title:this.projectBody.title, description: this.projectBody.description, userMembers: membersStr };

                this.$http.post(this.API_URL+'/api/v1/project/creation', bodyRequest).
                then(function (response) {
                    console.log("registration success");
                    if (response.status === 200) {
                        console.log('project created success');
                        this.$router.push('/home');
                    }
                }, function (err) {
                    this.$parent.errorNotify(err.body.message);
                })
            }


        }
    }
</script>

<style scoped>

</style>
