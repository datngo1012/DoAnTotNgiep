const ID_TOKEN_KEY = "nguoidung";

export const getToken = () => {
  return JSON.parse(window.localStorage.getItem(ID_TOKEN_KEY));
};

export const saveToken = (data) => {
  window.localStorage.setItem(ID_TOKEN_KEY, JSON.stringify(data));
};

export const destroyToken = () => {
  window.localStorage.removeItem(ID_TOKEN_KEY);
};

export default { getToken, saveToken, destroyToken };
