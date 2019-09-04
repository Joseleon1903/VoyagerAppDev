<template>
<div>

  <div class="panel panel-default">
    <div id="collapseOne" class="panel-collapse collapse in">
      <div class="panel-body">
        <div class="form-group">
          <label>Member project group </label>
          <div class="form-group input-group">
            <input type="text" placeholder="search member" class="form-control" v-model="searchText">
            <span class="input-group-btn">
            <button class="btn btn-default" type="submit" v-on:click="sendToSearchName"><i class="fa fa-search"></i></button>
        </span>
          </div>
        </div>

        <div class="form-group" v-for="mem in projectMember" v-if="projectMember" style="text-align: left">
          <input type="checkbox" id="checkbox" v-model="mem.checked">
          <label for="checkbox">{{mem.name}}</label>
        </div>      </div>
    </div>
  </div>



</div>


</template>

<script>
    export default {
        name: "ProjectMember",
        data () {
            return {
                projectMember: [],
                searchText:'',
                errors: [],
            }
        },
        methods:{

            sendToSearchName: function() {
                console.log("entering sendToSearchName");
                if (!this.searchText) {
                    this.$parent.$parent.errorNotify('search member name is required.');
                    return;
                }
                this.sendRequest();
            },
            sendRequest: function () {
                console.log("entering sendRequest");
                const jwtHeader = {'Authorization': 'Bearer ' + this.$session.get('token')};
                this.$http.get(this.API_URL+'/api/v1/project/search/member?name='+ this.searchText).
                then(function (response) {
                    console.log("search ");
                    if (response.status === 200) {
                        for(let index in response.body){
                            this.projectMember.push({name: response.body[index], checked: false})
                        }
                    }
                }, function (err) {
                    console.log("sendRequest error "+ err.body);
                    this.$parent.$parent.errorNotify(err.body.message);
                })
            },
            getMembers: function () {
                return this.projectMember;
            }
        }
    }
</script>

<style scoped>

</style>
