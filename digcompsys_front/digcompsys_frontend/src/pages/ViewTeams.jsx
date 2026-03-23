import { useEffect, useState } from "react";
import API from "../services/api";
import { useNavigate } from "react-router-dom";

function ViewTeams() {
  const [teams, setTeams] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchTeams = async () => {
      try {
        const res = await API.get("/teams");

        console.log("TEAMS RESPONSE:", res.data); // 🔍 DEBUG

        setTeams(Array.isArray(res.data) ? res.data : []);
      } catch (err) {
        console.log(err);
        setTeams([]);
      }
    };

    fetchTeams();
  }, []);

  return (
    <div className="container mt-5">
      <h2>All Teams</h2>

      <table className="table mt-3">
        <thead>
          <tr>
            <th>Team ID</th>
            <th>Team Name</th>
            <th>Team Lead ID</th>
            <th>Action</th>
          </tr>
        </thead>

        <tbody>
          {Array.isArray(teams) && teams.map(team => (
            <tr key={team.teamId}>
              <td>{team.teamId}</td>
              <td>{team.teamName}</td>
              <td>{team.teamLead?.userId}</td>

              <td>
                <button
                  className="btn btn-info btn-sm"
                  onClick={() => navigate(`/team/${team.teamId}`)}
                >
                  View Details
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ViewTeams;