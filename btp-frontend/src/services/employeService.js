import api from "../api/axios";

export const ajouterEmploye = (employe) => {
    return api.post("/employes", employe);
};

export const affecterEmploye = (employe) => {
    return api.post("/affecter", employe);
};

export const getEmployes = () => {
    return api.get("/employes/disponibles");
};
