import api from "../api/axios";

export const login = (username, password) => {
  return api.post("/auth/login", {
    username,
    password,
  });
};

export const logout = () => {
  localStorage.removeItem("token");
  localStorage.removeItem("role");
};
