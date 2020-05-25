import Layout from './components/layout';
import Datatable from 'react-bs-datatable';
import useSWR from 'swr';
import Link from 'next/link'
import { css } from 'emotion';

const header = [
  {
    title: 'Id',
    prop: 'id'
  },
  {
    title: 'Library',
    prop: 'name'
  },
  {
    title: 'Books',
    prop: 'id',
    cell: row => (
      <Link  href="/library-books/[id]" as={`/library-books/${row.id}`}>
       <button> View Books</button>
      </Link>
    )
  }
];

const classes = {
  table: 'table-striped table-hover',
  theadCol: css`
    .table-datatable__root & {
      font-weight: bold;
    }
  `
}

const customLabels = {
  noResults: 'No Libraries'
};

const fetcher = url => fetch(url).then(r => r.json())

export default function Index() {

  const { data, error } = useSWR('http://localhost:8080/library/list',fetcher)

  if(!data) return "Loading..."

  if(data)
  return (
    <Layout title="List of Libraries | Library Management System">
      <section className="jumbotron full_width">
        <div className="container">
          <div className="py-5 text-center full_width">
            <h2>Library Store</h2>
         </div>
            
          {/* Library listing starts */}  
          <div className="row full_width">
            <div className="col-md-12 order-md-1">
              <div className="d-flex justify-content-between  py-5">
                <h4 className="mb-3">List of Libraries </h4>
              </div>
             <Datatable
                tableHeaders={header}
                tableBody={data}
                initialSort={{ prop: 'id', isAscending: true }}
                labels={customLabels}
                classes={classes}
              />       
              
            </div> 
          </div>
          {/* Library listing ends */}
        </div>
      </section>
    </Layout>
  );
}