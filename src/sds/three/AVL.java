/*
 * This file is part of the SDSThree Open Source Project.
 * SDSThree is licensed under the GNU GPLv3.
 *
 * Copyright © 2020. Everton Bruno Silva dos Santos <evertonbrunogithub@yahoo.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package sds.three;

import java.io.Serializable;
import static java.lang.Math.max;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Consumer;
import sds.three.AVL.Leaf;

/**
 * Classe responsável por comportar-se como árvore AVL.
 * @author Everton Bruno Silva dos Santos.
 * @param <K> Refere-se ao tipo de chave usada.
 * @param <V> Refere-se ao tipo de valor usado.
 * @version 1.1
 */
public class AVL<K, V> implements Iterable<Leaf<K, V>>, Serializable {
    /**
     * Refere-se ao número de série da árvore AVL.
     */
    private static final long serialVersionUID = -9185946958686492020L;
    /**
     * Refere-se ao objeto comparador de chaves.
     */
    private final Comparator<K> comparator;
    /**
     * Refere-se ao objeto raiz da árvore.
     */
    private Node root;
    /**
     * Refere-se ao tamanho da árvore.
     */
    private int size;

    /**
     * Construtor responsável pelo instanciamento da árvore.
     * @param comparator Refere-se ao objeto comparador de chaves.
     * @throws NullPointerException Exceção lançada caso o comparador de chaves seja nulo.
     */
    public AVL(final Comparator<K> comparator) throws NullPointerException {
        if (comparator == null) {
            throw new NullPointerException();
        }
        this.comparator = comparator;
        this.size = 0;
    }

    /**
     * Método responsável por retornar o tamanho da árvore.
     * @return Retorna o tamanho da árvore.
     */
    public int size() {
        return size;
    }

    /**
     * Método responsável por esvaziar a árvore.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Método responsável por indicar se a árvore está vazia.
     * @return Retorna indicativo de que a árvore está vazia.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Método responsável por inserir um novo valor na árvore.
     * @param key   Refere-se a chave do dito valor.
     * @param value Refere-se ao dito valor.
     * @throws KeyUsedException Exceção lançada no caso da chave estar em uso.
     */
    public void put(final K key, final V value) throws KeyUsedException {
        root = put(key, value, root);
        size++;
    }

    /**
     * Método responsável por inserir um novo valor na árvore recursivamente.
     * @param key   Refere-se a chave do dito valor.
     * @param value Refere-se ao dito valor.
     * @param node  Refere-se ao elo atual da recursão.
     * @return Retorna árvore reconstruida com novo valor incluido.
     * @throws KeyUsedException Exceção lançada no caso da chave estar em uso.
     */
    private Node put(final K key, final V value, final Node node) throws KeyUsedException {
        if (node == null) {
            return new Node(key, value);
        }
        final int result = comparator.compare(node.key, key);
        if (result > 0) {
            node.left = put(key, value, node.left);
        } else if (result < 0) {
            node.right = put(key, value, node.right);
        } else {
            throw new KeyUsedException("Key used.");
        }
        return adjustHeight(node);
    }

    /**
     * Método responsável por encontrar um valor contido na árvore.
     * @param key Refere-se a chave de acesso ao dito valor.
     * @return Retorna o dito valor associado a chave.
     * @throws ValueNotFoundException Exceção lançada no caso do valor não ser encontrado.
     */
    public V find(final K key) throws ValueNotFoundException {
        return find(key, root);
    }

    /**
     * Método responsável por encontrar um valor contido na árvore recursivamente.
     * @param key  Refere-se a chave de acesso ao dito valor.
     * @param node Refere-se ao elo atual da recursão.
     * @return Retorna o dito valor associado a chave.
     * @throws ValueNotFoundException Exceção lançada no caso do valor não ser encontrado.
     */
    private V find(final K key, final Node node) throws ValueNotFoundException {
        if (node == null) {
            throw new ValueNotFoundException("Value not found.");
        }
        final int result = comparator.compare(node.key, key);
        if (result > 0) {
            return find(key, node.left);
        } else if (result < 0) {
            return find(key, node.right);
        } else {
            return node.value;
        }
    }

    /**
     * Método responsável por remover um valor contido na árvore.
     * @param key Refere-se a chave de acesso ao dito valor.
     * @throws ValueNotFoundException Exceção lançada no caso do valor não ser encontrado.
     */
    public void remove(final K key) throws ValueNotFoundException {
        root = remove(key, root);
        size--;
    }

    /**
     * Método responsável por remover um valor contido na árvore recursivamente.
     * @param key  Refere-se a chave de acesso ao dito valor.
     * @param node Refere-se ao elo atual da recursão.
     * @return Retorna árvore reconstruida com o dito valor ausente.
     * @throws ValueNotFoundException Exceção lançada no caso do valor não ser encontrado.
     */
    private Node remove(final K key, final Node node) throws ValueNotFoundException {
        if (node == null) {
            throw new ValueNotFoundException("Value not found.");
        }
        final int result = comparator.compare(node.key, key);
        if (result > 0) {
            node.left = remove(key, node.left);
        } else if (result < 0) {
            node.right = remove(key, node.right);
        } else if (node.isSubThree()) {
            Node tmpNode = node.left;
            while (node.right != null) {
                tmpNode = tmpNode.right;
            }
            final K tmpKey = tmpNode.key;
            final V tmpValue = tmpNode.value;
            tmpNode.key = node.key;
            tmpNode.value = node.value;
            node.key = tmpKey;
            node.value = tmpValue;
            node.left = remove(key, node.left);
        } else if (node.leftIsNotNull()) {
            return node.left;
        } else if (node.rightIsNotNull()) {
            return node.right;
        } else {
            return null;
        }
        return adjustHeight(node);
    }

    /**
     * Método responsável por retornar estrutura iterável de valores e chaves contidos na árvore.
     * @return Retorna estrutura iterável de valores e chaves contidos na árvore.
     */
    @Override
    public Iterator<Leaf<K, V>> iterator() {
        final MyIterator<Leaf<K, V>> myIterator = new MyIterator<>();
        forEach(leaf -> {
            myIterator.elements.add(leaf);
        });
        return myIterator;
    }

    /**
     * Método responsável por percorrer por valores e chaves contidos na árvore.
     * @param leaf Refere-se as folhas da árvore detentoras de valores e chaves.
     */
    @Override
    public void forEach(final Consumer<? super Leaf<K, V>> leaf) {
        forEach(leaf, root);
    }

    /**
     * Método responsável por percorrer por valores e chaves contidos na árvore recursivamente.
     * @param leaf Refere-se as folhas da árvore detentoras de valores e chaves.
     * @param node Refere-se ao elo atual da recursão.
     */
    private void forEach(final Consumer<? super Leaf<K, V>> leaf, final Node node) {
        if (node != null) {
            forEach(leaf, node.left);
            leaf.accept(node);
            forEach(leaf, node.right);
        }
    }

    /**
     * Método responsável por retornar a altura de determinado elo da árvore recursivamente.
     * @param node Refere-se ao elo atual da recursão.
     * @return Retorna altura de determinado elo da árvore recursivamente.
     */
    private int height(final Node node) {
        if (node == null) {
            return 0;
        }
        return max(height(node.left) + 1, height(node.right) + 1);
    }

    /**
     * Método responsável por atualizar o valor de balanceamento de determinado elo da árvore.
     * @param node Refere-se ao elo atual da recursão.
     */
    private void updateBalance(final Node node) {
        node.balancing = (height(node.right) - height(node.left));
    }

    /**
     * Método responsável por efetuar rotações simples a esquerda em elos da árvore.
     * @param newRoot Refere-se ao novo elo raiz.
     * @param oldRoot Refere-se ao antigo elo raiz.
     * @return Retorna elo raiz de árvore rotacionada a esquerda.
     */
    private Node simpleRotationLeft(final Node newRoot, final Node oldRoot) {
        oldRoot.left = newRoot.right;
        newRoot.right = oldRoot;
        return newRoot;
    }

    /**
     * Método responsável por efetuar rotações simples a direita em elos da árvore.
     * @param newRoot Refere-se ao novo elo raiz.
     * @param oldRoot Refere-se ao antigo elo raiz.
     * @return Retorna elo raiz de árvore rotacionada a direita.
     */
    private Node simpleRotationRight(final Node newRoot, final Node oldRoot) {
        oldRoot.right = newRoot.left;
        newRoot.left = oldRoot;
        return newRoot;
    }

    /**
     * Método responsável por efetuar rotações duplas a esquerda em elos da árvore.
     * @param left    Refere-se ao elo a esquerda.
     * @param oldRoot Refere-se ao antigo elo raiz.
     * @return Retorna elo raiz de árvore duplamente rotacionada a esquerda.
     */
    private Node doubleRotationLeft(final Node left, final Node oldRoot) {
        oldRoot.left = left.right;
        left.right = oldRoot.left.left;
        oldRoot.left.left = left;
        return simpleRotationLeft(oldRoot.left, oldRoot);
    }

    /**
     * Método responsável por efetuar rotações duplas a direita em elos da árvore.
     * @param right   Refere-se ao elo a direita.
     * @param oldRoot Refere-se ao antigo elo raiz.
     * @return Retorna elo raiz de árvore duplamente rotacionada a direita.
     */
    private Node doubleRotationRight(final Node right, final Node oldRoot) {
        oldRoot.right = right.left;
        right.left = oldRoot.right.right;
        oldRoot.right.right = right;
        return simpleRotationRight(oldRoot.right, oldRoot);
    }

    /**
     * Método responsável por ajustar a altura de determinado elo da árvore.
     * @param node Refere-se ao dito elo a ter sua altura ajustada.
     * @return Retorna elo raiz da árvore reconstruida com altura ajustada.
     */
    private Node adjustHeight(final Node node) {
        updateBalance(node);
        if (node.balancing <= -2) {
            if (node.balancing * node.left.balancing > 0) {
                return simpleRotationLeft(node.left, node);
            } else {
                return doubleRotationLeft(node.left, node);
            }
        } else if (node.balancing >= 2) {
            if (node.balancing * node.right.balancing > 0) {
                return simpleRotationRight(node.right, node);
            } else {
                return doubleRotationRight(node.right, node);
            }
        }
        return node;
    }

    /**
     * Interface responsável por fornecer as assinaturas dos métodos de uma folha.
     * @author Everton Bruno Silva dos Santos.
     * @param <K> Refere-se ao tipo de chave contida na folha.
     * @param <V> Refere-se ao tipo de valor contido na folha.
     * @version 1.0
     */
    public interface Leaf<K, V> extends Serializable {

        /**
         * Método responsável por retornar chave contida na folha.
         * @return Retorna chave contida na folha.
         */
        K getKey();

        /**
         * Método responsável por retornar valor contido na folha.
         * @return Retorna valor contido na folha.
         */
        V getValue();

    }

    /**
     * Classe responsável por comportar-se como elo da árvore.
     * @author Everton Bruno Silva dos Santos.
     * @version 1.0
     */
    private class Node implements Leaf<K, V> {
        /**
         * Refere-se ao número de série do elo da árvore AVL.
         */
        private static final long serialVersionUID = -9185946958686495728L;
        /**
         * Refere-se a chave contida no elo.
         */
        private K key;
        /**
         * Refere-se ao valor contido no elo.
         */
        private V value;
        /**
         * Refere-se ao valor de balanceamento contido no elo.
         */
        private int balancing;
        /**
         * Refere-se ao elo a esquerda.
         */
        private Node left;
        /**
         * Refere-se ao elo a direita.
         */
        private Node right;

        /**
         * Construtor responsável pelo instanciamento do elo.
         * @param key   Refere-se a chave contida no elo.
         * @param value Refere-se ao valor contido no elo.
         */
        private Node(final K key, final V value) {
            this.key = key;
            this.value = value;
            this.balancing = 0;
        }

        /**
         * Método responsável por indicar se o elo é uma sub-árvore.
         * @return Retorna indicativo de que o elo é uma sub-árvore.
         */
        private boolean isSubThree() {
            return leftIsNotNull() && rightIsNotNull();
        }

        /**
         * Método responsável por indicar se o elo não é nulo a esquerda.
         * @return Retorna indicativo de que o elo não é nulo a esquerda.
         */
        private boolean leftIsNotNull() {
            return left != null;
        }

        /**
         * Método responsável por indicar se o elo não é nulo a direita.
         * @return Retorna indicativo de que o elo não é nulo a direita.
         */
        private boolean rightIsNotNull() {
            return right != null;
        }

        /**
         * Método responsável por retornar chave contida no elo.
         * @return Retorna chave contida no elo.
         */
        @Override
        public K getKey() {
            return key;
        }

        /**
         * Método responsável por retornar valor contido no elo.
         * @return Retorna valor contido no elo.
         */
        @Override
        public V getValue() {
            return value;
        }

    }

    /**
     * Classe responsável por comportar-se como uma estrutura iterável.
     * @author Everton Bruno Silva dos Santos.
     * @param <T> Refere-se ao tipo de dados iterável.
     * @version 1.0
     */
    private class MyIterator<T> implements Iterator<T>, Serializable {
        /**
         * Refere-se ao número de série do iterador da árvore.
         */
        private static final long serialVersionUID = -2020946958686495728L;
        /**
         * Refere-se aos elementos contidos no iterador.
         */
        private final LinkedList<T> elements;

        /**
         * Construtor responsável pelo instanciamento da estrutura iterável.
         */
        private MyIterator() {
            this.elements = new LinkedList<>();
        }

        /**
         * Método responsável por indicar se há um próximo elemento.
         * @return Retorna indicativo de que há um próximo elemento.
         */
        @Override
        public boolean hasNext() {
            return !elements.isEmpty();
        }

        /**
         * Método responsável por retornar um próximo elemento.
         * @return Retorna um próximo elemento.
         */
        @Override
        public T next() {
            return elements.removeFirst();
        }

    }

}
