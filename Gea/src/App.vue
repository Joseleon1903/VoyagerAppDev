<template>
  <div id="app">
    <router-view/>
    <notifications group="foo" position="bottom right" />
  </div>
</template>

<script>
export default {
  name: 'App',
    data () {
        return {
            formData:{ firstName: '', lastName: '', email: '' , confirmPassword: '', password: '' },
            errors: [],
        }
    },
    methods:{
        errorNotify: function (message) {
            this.$notify({
                group: 'foo',
                type:'error',
                title: 'invalid input',
                text: message
            });
        },

    }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
