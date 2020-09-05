import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    component: () => import("@/layouts/home/Index.vue"),
    children: [
      {
        path: "",
        name: "Home",
        component: () => import("@/views/home/Index.vue"),
      },
      {
        path: "signin",
        name: "SignIn",
        component: () => import("@/views/signin/SignIn.vue"),
      },
      {
        path: "signup",
        name: "SignUp",
        component: () => import("@/views/signup/SignUp.vue"),
      },
      {
        path: "about",
        name: "About",
        component: () => import("@/views/about/Index.vue"),
        meta: { src: require("@/assets/about.jpg") },
      },
      {
        path: "contact-us",
        name: "Contact",
        component: () => import("@/views/contact-us/Index.vue"),
        meta: { src: require("@/assets/contact.jpg") },
      },
      // {
      //   path: '*',
      //   name: 'FourOhFour',
      //   component: () => import('@/views/404/Index.vue'),
      // },
    ],
  },
  {
    path: "/backend",
    component: () => import("@/layouts/backend/Index.vue"),
    children: [
      {
        path: "thongkechung",
        name: "Thongkechung",
        component: () => import("@/views/backend/Thongkechung.vue"),
      },
      {
        path: "danhsachdonhang",
        name: "Danhsachdonhang",
        component: () =>
          import("@/views/backend/quanlynhaphang/Danhsachdonhang.vue"),
      },
      {
        path: "timkiemsanpham",
        name: "Timkiemsanpham",
        component: () =>
          import("@/views/backend/quanlynhaphang/Timkiemsanpham.vue"),
      },
      {
        path: "thongtincanhan",
        name: "Thongtincanhan",
        component: () =>
          import("@/views/backend/quanlycanhan/Thongtincanhan.vue"),
      },
      {
        path: "doimatkhau",
        name: "Doimatkhau",
        component: () => import("@/views/backend/quanlycanhan/Doimatkhau.vue"),
      },
      {
        path: "naptien",
        name: "Naptien",
        component: () => import("@/views/backend/quanlyvitien/Naptien.vue"),
      },
      {
        path: "giaodich",
        name: "Giaodich",
        component: () => import("@/views/backend/quanlyvitien/Giaodich.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
