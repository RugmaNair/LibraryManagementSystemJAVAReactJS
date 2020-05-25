import React from "react";
import Layout from '../components/layout';
import Datatable from 'react-bs-datatable';
import { useRouter } from 'next/router';
import Link from 'next/link';
import useSWR from 'swr';
import { css } from 'emotion';

const header = [
  {
    title: 'Id',
    prop: 'id'
  },
  {
    title: 'Title',
    prop: 'title'
  },{
    title: 'Author',
    prop: 'author'
  },{
    title: 'Publisher',
    prop: 'publisher'
  },{
    title: 'Pages',
    prop: 'pages'
  },{
    title: 'Quality Available',
    prop: 'available'
  },
];

const customLabels = {
  noResults: 'No books available'
};

const classes = {
  table: 'table-striped table-hover',
  theadCol: css`
    .table-datatable__root & {
      font-weight: bold;
    }
  `
}

const fetcher = url => fetch(url).then(r => r.json())

export default function LibraryBooks() {
  const router = useRouter()
  const { id } = router.query
  console.log(id);
  const { data, error } = useSWR('http://localhost:8080/library/'+id,fetcher)

  if(!data) return "Loading...";
  if(data)
  return (
    <Layout title= {data.name+" | TestProject"}>
      <section class="jumbotron full_width">
        <div class="container">
          <div class="py-5 text-center full_width">
            <h2>Library Store</h2>
          </div>
          <Link  href="/" >
          <button>Back</button>
          </Link>
          {/* book listing starts */}  
          <div class="row full_width">
            <div class="col-md-12 order-md-1">
              <div className="d-flex justify-content-between  py-5">
                <h4 class="mb-3">List of Books in {data.name}</h4>
              </div>
              <Datatable
                tableHeaders={header}
                tableBody={data.books}
                labels={customLabels}
                classes={classes}
              />            
              
            </div>
          </div>
          {/* book listing ends */}
        </div>
      </section>
    </Layout>
  );
}