import { List, Card } from '_antd@3.16.3@antd/lib/index';
import { Button } from '_antd@3.16.3@antd/lib/index';
import router from 'umi/router';

function addOwner() {
  router.replace('/owner/addOwner');
}

export default function(data) {
  data = [
    {
      name: 'Owner 1',
    },
    {
      name: 'Owner 2',
    },
    {
      name: 'Owner 3',
    },
    {
      name: 'Owner 4',
    },
    {
      name: 'Owner 5',
    },
    {
      name: 'Owner 6',
    },
    {
      name: 'Owner 7',
    },
    {
      name: 'Owner 8',
    },
  ];
  return(
    <div>
      <Button type="primary" onClick={()=>addOwner()}>New</Button>
    <List
      grid={{
        gutter: 16, xs: 1, sm: 2, md: 4, lg: 4, xl: 3, xxl: 3,
      }}
      dataSource={data}
      renderItem={item => (
        <List.Item actions={[<a>edit</a>, <a>delete</a>,<a>Pets List</a>]}>
          <Card title={item.name}>Card content</Card>
        </List.Item>
      )}
    />
    </div>
  );
}
