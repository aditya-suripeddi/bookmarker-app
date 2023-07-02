import '@/styles/globals.css'
import type { AppProps } from 'next/app'
import 'bootstrap/dist/css/bootstrap.min.css' // to globally import something, import it in _app.tsx
export default function App({ Component, pageProps }: AppProps) {
  return <Component {...pageProps} />
}
