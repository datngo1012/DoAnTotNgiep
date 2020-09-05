module.exports = {
  transpileDependencies: ["vuetify"],
  publicPath:
    process.env.NODE_ENV === "production"
      ? "/alone/" // Thay tên repository của các bạn vào đây nhé
      : "/",
};
