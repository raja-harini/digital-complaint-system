import { useState } from "react";
import API from "../services/api";
import { useParams } from "react-router-dom";

function SendNotification() {
  const { id } = useParams(); // complaintId

  const [message, setMessage] = useState("");
  const [type, setType] = useState("");

  // ✅ Get userId from token
  const token = localStorage.getItem("token");
  const decoded = JSON.parse(atob(token.split(".")[1]));
  const userId = decoded.userId;

  const handleSend = async (e) => {
    e.preventDefault();

    if (!type) {
      alert("Please select notification type");
      return;
    }

    try {
      await API.post("/notifications", {
        userId: userId,
        complaintId: Number(id),
        notificationType: type, // ✅ matches enum
        message: message
      });

      alert("Notification sent");

      // reset form
      setMessage("");
      setType("");

    } catch (error) {
      console.log(error);
      alert("Failed to send notification");
    }
  };

  return (
    <div className="container mt-5">
      <h3>Send Notification</h3>

      <form onSubmit={handleSend}>

        {/* ✅ Dropdown for enum */}
        <select
          className="form-control mt-3"
          value={type}
          onChange={(e) => setType(e.target.value)}
        >
          <option value="">-- Select Notification Type --</option>
          <option value="COMPLAINT_SUBMITTED">Complaint Submitted</option>
          <option value="COMPLAINT_UPDATED">Complaint Updated</option>
          <option value="COMPLAINT_RESOLVED">Complaint Resolved</option>
          <option value="ADMIN_RESPONSE">Admin Response</option>
          <option value="SYSTEM_ALERT">System Alert</option>
        </select>

        {/* ✅ Message input */}
        <input
          type="text"
          className="form-control mt-3"
          placeholder="Enter notification message..."
          value={message}
          onChange={(e) => setMessage(e.target.value)}
        />

        <button className="btn btn-primary mt-3" type="submit">
          Send Notification
        </button>

      </form>
    </div>
  );
}

export default SendNotification;