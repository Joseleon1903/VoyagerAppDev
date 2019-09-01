<template>

  <div id="wrapper">

    <HeaderBase/>

    <MenuNav/>


 </div>

</template>

<script>
    import HeaderBase from '@/components/layouts/HeaderBase'
    import MenuNav from '@/components/layouts/MenuNav'

    export default {
        name: "CreationProject",
        components: {
            HeaderBase,
            MenuNav
        },
        mounted() {
            console.log("init mounted");
            console.log("user: "+   this.$session.get('username'));
            console.log("token: "+   this.$session.get('token'));
            console.log("status: "+   this.$session.get('status'));
        }
    }
</script>

<style scoped>

</style>
