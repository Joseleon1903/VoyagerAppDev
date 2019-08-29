// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Notifications from 'vue-notification'
import VueSession from 'vue-session'
import VueResources from 'vue-resource'

let options = {
  persist: true
};

Vue.use(VueSession, options);
Vue.config.productionTip = false;
Vue.use(Notifications);
Vue.use(VueResources);
// This is a global mixin, it is applied to every vue instance
Vue.mixin({
  data: function() {
    return {
      globalVar:'global',
      API_URL: 'http://localhost:8086'
    }
  }
})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
  created: function() {
    console.log('main init vue app');

  }
});
