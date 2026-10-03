import api from "../api/axios";

export const getMateriaux = () => {
    return api.get("/materiaux");
};
