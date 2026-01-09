import api from "../api/axios";

/**
 * Récupérer tous les matériaux depuis la BD
 */
export const getMateriaux = () => {
    return api.get("/materiaux");
};
