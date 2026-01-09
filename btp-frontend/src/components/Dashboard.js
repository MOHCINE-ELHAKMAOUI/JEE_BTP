import MateriauList from "./MateriauList";
import DevisView from "./DevisView";

export default function Dashboard() {
    return (
        <div className="space-y-6">
            <MateriauList />
            <DevisView projetId={1} /> {/* ID EXISTANT */}
        </div>
    );
}
