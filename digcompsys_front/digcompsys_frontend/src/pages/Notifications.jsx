import { useEffect, useState } from "react";
import API from "../services/api";

function Notifications() {
  const [notifications, setNotifications] = useState([]);

  const fetchNotifications = async () => {
    try {
      const res = await API.get("/notifications");
      setNotifications(res.data);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    fetchNotifications();
  }, []);

  const toggleStatus = async (id) => {
    try {
      await API.put(`/notifications/${id}/toggle`);
      fetchNotifications();
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <div className="container mt-5">
      <h2>Notifications</h2>

      <ul className="list-group mt-3">
        {notifications.map((n) => (
          <li
            key={n.notificationId}
            className={`list-group-item d-flex justify-content-between align-items-center 
              ${n.readFlag ? "list-group-item-light" : "list-group-item-warning"}`}
          >
            <span>
              {n.message}
              <br />
              <small>Complaint: {n.complaint?.title}</small>
            </span>

            <button
              className={`btn btn-sm ${n.readFlag ? "btn-secondary" : "btn-success"}`}
              onClick={() => toggleStatus(n.notificationId)}
            >
              {n.readFlag ? "Mark Unread" : "Mark Read"}
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Notifications;