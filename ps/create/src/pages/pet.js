import { List, Card } from 'antd';

const data = [
  {
    name: 'Pet 1',
    type: 'Type 1',
    owner: 'Owner 1'
  },
  {
    name: 'Pet 2',
    type: 'Type 1',
    owner: 'Owner 1'
  },
  {
    name: 'Pet 3',
    type: 'Type 1',
    owner: 'Owner 1'
  },
  {
    name: 'Pet 4',
    type: 'Type 1',
    owner: 'Owner 1'
  },
  {
    name: 'Pet 5',
    type: 'Type 1',
    owner: 'Owner 1'
  },
  {
    name: 'Pet 6',
    type: 'Type 1',
    owner: 'Owner 1'
  },
];

export default function() {
  return(
    <List
      grid={{
        gutter: 16, xs: 1, sm: 2, md: 4, lg: 4, xl: 6, xxl: 3,
      }}
      dataSource={data}
      renderItem={item => (
        <List.Item actions={[<a>edit</a>, <a>delete</a>,<a>Pets List</a>]}>
          <Card title={item.name}>Pet Type:{item.type}
          Owner:{item.owner}</Card>
        </List.Item>
      )}
    />
  );
}
