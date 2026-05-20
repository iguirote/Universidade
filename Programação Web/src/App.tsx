//import { useState } from 'react'
import './App.css'
import { useProdutoDados } from './hooks/UseProduto.ts';
import { CartaoProduto } from './componente/Card.tsx';

function App() {
  const { data } = useProdutoDados();

  return (
    <main className="container">
        <h1>MenuStream - Produtos</h1>

        <section className="grade-cartoes">
            {data?.map((produto ) => (
                <CartaoProduto key={produto.id} produto={produto} />
             ))}
        </section>
    </main>
  )
}

export default App
