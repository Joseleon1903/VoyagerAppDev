<template>
<div>

    <div class="form-group">
      <label>Member project group </label>
      <div class="form-group input-group">
        <input type="text" placeholder="search member" class="form-control" v-model="searchText">
        <span class="input-group-btn">
            <button class="btn btn-default" type="submit" v-on:click="sendToSearchName"><i class="fa fa-search"></i></button>
        </span>
      </div>
    </div>

    <div class="form-group">
      <label>Users</label>
      <div class="checkbox" >
        <label>
          <input type="checkbox" >
        </label>
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

            },
            sendRequest: function () {
                console.log("entering sendRequest");
                this.$http.get(this.API_URL+'/project/search/member', {name: this.searchText}).
                then(function (response) {
                    console.log("registration success");
                    if (response.status === 200) {
                        this.projectMember = response.body;
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
