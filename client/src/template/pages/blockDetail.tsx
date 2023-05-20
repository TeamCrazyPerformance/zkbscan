import Nav from "react-bootstrap/Nav";

interface MyComponentProps {
  data: {
    [key: string]: string;
  };
}
const blockDetail = ({ data }: MyComponentProps) => {
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
            </Nav>
          </div>
        </div>
      </div>
    </div>
  );
};

export default blockDetail;
