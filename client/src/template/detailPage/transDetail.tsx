import Nav from "react-bootstrap/Nav";

interface MyComponentProps {
  data: {
    [key: string]: string;
  };
}
const transDetail = ({ data }: MyComponentProps) => {
  return (
    <div>
      <div className="wrapper">
        <div></div>
        <div>
          <div>
            <Nav variant="tabs" defaultActiveKey="/overview">
              <Nav.Item>
                <Nav.Link href="/overview">Overview</Nav.Link>
              </Nav.Item>
              <Nav.Item>
                <Nav.Link eventKey="log">Log</Nav.Link>
              </Nav.Item>
            </Nav>
          </div>
        </div>
      </div>
    </div>
  );
};

export default transDetail;
