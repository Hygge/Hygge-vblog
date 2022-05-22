import Vue from 'vue'
import Router from 'vue-router'
import Home from "@/views/Home";
import Guidang from "@/views/Guidang";
import About from "@/views/About";
import Category from "@/views/Category";
import Login from "@/views/admin/Login"
import Index from "@/views/Index"
import Register from "@/views/admin/Register"
import Admin from "@/views/admin/Admin";
import Error from "@/views/Error"
import BlogInfo from "@/views/BlogInfo";
import AdBlogs from "@/views/admin/AdBlogs";
import BlogEdit from "@/views/admin/BlogEdit";
import AdTag from "@/views/admin/AdTag";
import UserInfo from "@/views/admin/UserInfo";
import AdCategory from "@/views/admin/AdCategory";
import AdHuisouzan from "@/views/admin/AdHuisouzan";
import AdContent from "@/views/admin/AdContent";
import Annex from "../views/admin/Annex";
import Titles from "../views/Titles";


Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: 'Index',
            component: Index
        },
        {
            path: '/index',
            name: 'Index',
            component: Index
        },
        {
            path: '/home',
            name: 'Home',
            component: Home
        },
        {
            path: '/guidang',
            name: 'guidang',
            component: Guidang
        },
        {
            path: '/about',
            name: 'about',
            component: About
        },
        {
            path: '/category',
            name: 'category',
            component: Category
        },
        {
            path: '/error',
            name: 'error',
            component: Error
        },
        {
            path: '/titles',
            name: 'titles',
            component: Titles
        },

        {
            path: '/blogInfo/:blogId',
            name: 'BlogInfo',
            component: BlogInfo
        },



        // admin管理页面
        {
            path: '/login',
            name: 'login',
            component: Login
        },
        {
            path: '/register',
            name: 'register',
            component: Register
        },
        {
          path: '/admin',
          name: 'admin',
          meta: {
              requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
          component: Admin

        },
        {
            path:'/admin/blogs',
            name:'AdBlogs',
            component: AdBlogs,
            meta:{
                requiresAuth: true
            }
        },
        {
            path:'/admin/blog/add',
            name:'BlogAdd',
            component: BlogEdit,
            meta:{
                requiresAuth: true
            }
        },
        {
            path:'/admin/blog/edit/:blogId',
            name:'BlogEdit',
            component: BlogEdit,
            meta:{
                requiresAuth: true
            }
        },
        {
            path:'/admin/tag',
            name:'AdTag',
            component: AdTag,
            meta:{
                requiresAuth: true
            }
        },
        {
            path:'/admin/userInfo',
            name:'UserInfo',
            component: UserInfo,
            meta:{
                requiresAuth: true
            }
        },
        {
            path:'/admin/AdCategory',
            name:'AdCategory',
            component: AdCategory,
            meta:{
                requiresAuth: true
            }
        },
        {
            path:'/admin/AdHuisouzan',
            name:'AdHuisouzan',
            component: AdHuisouzan,
            meta:{
                requiresAuth: true
            }
        },
        {
            path:'/admin/AdContent',
            name:'AdContent',
            component: AdContent,
            meta:{
                requiresAuth: true
            }
        },
        {
            path:'/admin/Annex',
            name:'Annex',
            component: Annex,
            meta:{
                requiresAuth: true
            }
        },

    ],

})
