const FloatingButton = ({ label, action }) => (
  <span className="floating-button" onClick={action}>
    {label}
  </span>
);

export default FloatingButton;
