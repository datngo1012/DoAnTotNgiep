const ID_TOKEN_KEY = "user_data";

export const getToken = () => {
  return JSON.parse(window.localStorage.getItem(ID_TOKEN_KEY));
};

export const saveToken = (token) => {
  window.localStorage.setItem(ID_TOKEN_KEY, JSON.stringify(token));
};

export const destroyToken = () => {
  window.localStorage.removeItem(ID_TOKEN_KEY);
};

export default { getToken, saveToken, destroyToken };
