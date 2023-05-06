<template>
    <NavBar/>
    <router-view/>
</template>

<script>

import 'bootstrap/dist/js/bootstrap';
import 'bootstrap/dist/css/bootstrap.css';
import NavBar from "@/components/NavBar.vue";
// import $ from 'jquery';

export default {
    components: {
        NavBar
    },
    setup() {
        // $.ajax({
        //     url: 'http://localhost:3000/user/account/register/',
        //     type: 'post',
        //     data: {
        //         username: 'yxca',
        //         password: '123',
        //         confirmedPassword: '123',
        //     },
        //     success(resp) {
        //         console.log(resp);
        //     },
        //     error(resp) {
        //         console.log(resp);
        //     }
        // });
    }
}

</script>

<style>

body {
    background-size: cover;
    background-image: url('@/assets/images/background.png');
}

</style>
