import api from "../api/axios";

export const login = (email, motDePasse) => {
  return api.post("/auth/login", {
    email,
    motDePasse,
  });
};

export const register = (registerData) => {
  return api.post("/auth/register", registerData);
};

export const logout = () => {
  localStorage.removeItem("user");
  localStorage.removeItem("token");
  localStorage.removeItem("role");
};
