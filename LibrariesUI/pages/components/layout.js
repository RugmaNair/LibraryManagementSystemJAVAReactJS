import Head from 'next/head';

export default function Layout({
  children,
  title = 'Library Store',
  description = '',
}) {
  return (
    <div className="container-fluid">
      <div className="row">
      <Head>
        <meta charSet="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta httpEquiv="X-UA-Compatible" content="ie=edge" />
        <meta name="theme-color" content="#000" />
        {/* <link rel="shortcut icon" type="image/x-icon" href="/public/images/favicon.ico" /> */}
        <title>{title}</title>
        <meta name="description" content={description || ''} />
        <link rel="stylesheet" href="/css/bootstrap.min.css" />
        <link rel="stylesheet" href="/fonts/font-awesome/css/font-awesome.css" />
        <link rel="stylesheet" href="/css/style.css" />
      </Head>
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark full_width">
        <div className="container d-flex justify-content-between">
        <a className="navbar-brand" href="/">Library Management System</a>
        </div>
      </nav>

      {children}

      <footer className="footer mt-auto py-3">
          <p className="text-muted">Developed by Rugma R Nair</p>
      </footer>
    </div>
    </div>
  )
}