import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import API from "../services/api";

function TeamDetails() {
  const { id } = useParams();
  const [team, setTeam] = useState(null);

  useEffect(() => {
    API.get(`/teams/${id}`)
      .then(res => setTeam(res.data))
      .catch(err => console.log(err));
  }, [id]);

  if (!team) return <p>Loading...</p>;

  return (
    <div className="container mt-5">
      <h2>Team Details</h2>

      <p><b>Team ID:</b> {team.teamId}</p>
      <p><b>Team Name:</b> {team.teamName}</p>
      <p><b>Contact:</b> {team.contact}</p>

      <h4 className="mt-4">Team Lead</h4>
      <p>{team.teamLead?.userName} (ID: {team.teamLead?.userId})</p>

      <h4 className="mt-4">Employees</h4>

      <ul className="list-group">
        {team.employees?.map(emp => (
          <li key={emp.userId} className="list-group-item">
            {emp.userName} (ID: {emp.userId})
          </li>
        ))}
      </ul>
    </div>
  );
}

export default TeamDetails;