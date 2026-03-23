import { useNavigate } from "react-router-dom";

function EmployeeDashboard() {
  const navigate = useNavigate();

  return (
    <div className="container mt-5 text-center">
      <h2>Employee Dashboard</h2>

      <div className="mt-4">
        <button onClick={() => navigate("/employee/complaints")} className="btn btn-primary m-2">
          View Complaints
        </button>

        <button onClick={() => navigate("/upload-details")} className="btn btn-success m-2">
          Upload Details
        </button>
      </div>
    </div>
  );
}

export default EmployeeDashboard;