import api from "../api/axios";

export const ajouterTerrain = (terrain) => {
    return api.post("/terrains", terrain);
};

export const getTerrains = () => {
    return api.get("/terrains");
};
