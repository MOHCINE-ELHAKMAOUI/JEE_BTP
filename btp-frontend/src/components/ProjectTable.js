import { useState, useEffect } from "react";
import { getProjets } from "../services/projetService";
import { Link } from "react-router-dom";

function ProjetTable() {
  const [projets, setProjets] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    fetchProjets();
  }, []);

  const fetchProjets = async () => {
    try {
      const res = await getProjets();
      setProjets(res.data);
      setLoading(false);
    } catch (err) {
      console.error(err);
      setError("Erreur lors du chargement des projets");
      setLoading(false);
    }
  };

  if (loading) return <p>Chargement des projets...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div>
      <h2>Liste des projets</h2>
      <Link to="/projets/new">
        <button>Créer un nouveau projet</button>
      </Link>
      <table border="1" cellPadding="10" cellSpacing="0">
        <thead>
          <tr>
            <th>ID</th>
            <th>Terrain</th>
            <th>Type</th>
            <th>Superficie</th>
            <th>Coût total (DH)</th>
            <th>Statut</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {projets.map((p) => (
            <tr key={p.id}>
              <td>{p.id}</td>
              <td>{p.terrain?.localisation || "N/A"}</td>
              <td>{p.typeConstruction}</td>
              <td>{p.superficieConstruite}</td>
              <td>{p.coutTotal || 0}</td>
              <td>{p.statut}</td>
              <td>
                <Link to={`/projets/${p.id}`}>
                  <button>Détails</button>
                </Link>
                
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ProjetTable;
