<template>
    <NavBar/>
    <router-view/>
</template>

<script>

import 'bootstrap/dist/js/bootstrap';
import 'bootstrap/dist/css/bootstrap.css';
import NavBar from "@/components/NavBar.vue";

export default {
    components: {
        NavBar
    }
}

</script>

<style>

body {
    background-size: cover;
    background-image: url('@/assets/images/background.png');
}

</style>
