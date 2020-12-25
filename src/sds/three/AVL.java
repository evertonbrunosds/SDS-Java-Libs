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

import sds.util.FileStream;
import sds.util.KeyUsedException;
import sds.util.Comparator;
import sds.util.EntryNotFoundException;
import sds.util.Duplicable;
import java.io.Serializable;
import java.util.Map.Entry;
import static java.lang.Math.max;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Consumer;

/**
 * Classe responsável por comportar-se como árvore AVL.
 * @author Everton Bruno Silva dos Santos.
 * @param <K> Refere-se ao tipo de chave usada nas entradas.
 * @param <V> Refere-se ao tipo de valor usado nas entradas.
 * @version 1.4
 */
public class AVL<K, V> implements Iterable<Entry<K, V>>, Serializable, Duplicable<AVL<K, V>>, FileStream<AVL<K, V>> {
    /**
     * Refere-se ao número de série da árvore AVL.
     */
    private static final long serialVersionUID = -9185946958686492020L;
    /**
     * Refere-se ao objeto comparador de chaves.
     */
    private Comparator<K> comparator;
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
     * Construtor responsável pelo instanciamento da árvore.
     * @param comparator Refere-se ao objeto comparador de chaves.
     * @param root       Refere-se ao objeto raiz da árvore.
     * @param size       Refere-se ao tamanho da árvore.
     */
    private AVL(final Comparator<K> comparator, final Node root, final int size) {
        this.comparator = comparator;
        this.root = root;
        this.size = size;
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
     * Método responsável por inserir uma nova entrada na árvore.
     * @param key   Refere-se a chave da dita entrada.
     * @param value Refere-se ao valor da dita entrada.
     * @throws KeyUsedException Exceção lançada no caso da chave estar em uso por outra entrada.
     */
    public void put(final K key, final V value) throws KeyUsedException {
        root = put(key, value, root);
        size++;
    }

    /**
     * Método responsável por inserir uma nova entrada na árvore recursivamente.
     * @param key   Refere-se a chave da dita entrada.
     * @param value Refere-se ao valor da dita entrada.
     * @param node  Refere-se ao elo atual da recursão.
     * @return Retorna árvore reconstruída com nova entrada incluída.
     * @throws KeyUsedException Exceção lançada no caso da chave estar em uso por outra entrada.
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
     * Método responsável por encontrar uma entrada contida na árvore.
     * @param key Refere-se a chave de acesso à dita entrada.
     * @return Retorna a dita entrada detentora da chave.
     * @throws EntryNotFoundException Exceção lançada no caso da entrada não ser encontrada.
     */
    public Entry<K, V> find(final K key) throws EntryNotFoundException {
        final Entry<K, V> entry = find(key, root);
        if (entry == null) {
            throw new EntryNotFoundException("Entry not found.");
        }
        return entry;
    }

    /**
     * Método responsável por indicar se determinada entrada contém dada chave.
     * @param key Refere-se a dita chave.
     * @return Retorna indicativo de que determinada entrada contém dada chave.
     */
    public boolean containsKey(final K key) {
        return find(key, root) != null;
    }

    /**
     * Método responsável por encontrar uma entrada contida na árvore recursivamente.
     * @param key  Refere-se a chave de acesso à dita entrada.
     * @param node Refere-se ao elo atual da recursão.
     * @return Retorna a dita entrada detentora da chave.
     */
    private Node find(final K key, final Node node) {
        if (node == null) {
            return null;
        }
        final int result = comparator.compare(node.key, key);
        if (result > 0) {
            return find(key, node.left);
        } else if (result < 0) {
            return find(key, node.right);
        } else {
            return node;
        }
    }

    /**
     * Método responsável por alterar o valor de determinada entrada na árvore.
     * @param key   Refere-se a chave de acesso à entrada.
     * @param value Refere-se ao novo valor da entrada.
     * @return Retorna o antigo valor da chave.
     * @throws EntryNotFoundException Exceção lançada no caso da entrada não ser encontrada.
     */
    public V setValue(final K key, final V value) throws EntryNotFoundException {
        return find(key).setValue(value);
    }

    /**
     * Método responsável por substituir dada chave de uma entrada por outra na árvore.
     * @param currentKey Refere-se a chave atual.
     * @param newKey     Refere-se a nova chave.
     * @throws KeyUsedException       Exceção lançada no caso da nova chave estar em uso por outra entrada.
     * @throws EntryNotFoundException Exceção lançada no caso da entrada não ser encontrada.
     */
    public void setKey(final K currentKey, final K newKey) throws KeyUsedException, EntryNotFoundException {
        final Node node = find(currentKey, root);
        final Node otherNode = find(newKey, root);
        if (node == null) {
            throw new EntryNotFoundException("Entry not found.");
        } else if (otherNode != null && !node.equals(otherNode)) {
            throw new KeyUsedException("Key used.");
        } else if (!node.equals(otherNode)) {
            remove(node.key);
            node.key = newKey;
            put(node.key, node.value);
        }
    }

    /**
     * Método responsável por duplicar a árvore.
     * @return Retorna árvore duplicata.
     */
    @Override
    public AVL<K, V> duplicate() {
        return !isEmpty() ? new AVL<>(comparator, root.duplicate(), size) : new AVL<>(comparator);
    }

    /**
     * Método responsável por remover uma entrada contida na árvore.
     * @param key Refere-se a chave de acesso à dita entrada.
     * @throws EntryNotFoundException Exceção lançada no caso da entrada não ser encontrada.
     */
    public void remove(final K key) throws EntryNotFoundException {
        root = remove(key, root);
        size--;
    }

    /**
     * Método responsável por remover uma entrada contida na árvore recursivamente.
     * @param key  Refere-se a chave de acesso à dita entrada.
     * @param node Refere-se ao elo atual da recursão.
     * @return Retorna árvore reconstruída com o dita entrada ausente.
     * @throws EntryNotFoundException Exceção lançada no caso da entrada não ser encontrada.
     */
    private Node remove(final K key, final Node node) throws EntryNotFoundException {
        if (node == null) {
            throw new EntryNotFoundException("Entry not found.");
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
     * Método responsável por retornar estrutura iterável de entradas contidos na árvore.
     * @return Retorna estrutura iterável de entradas contidos na árvore.
     */
    @Override
    public Iterator<Entry<K, V>> iterator() {
        final MyIterator<Entry<K, V>> myIterator = new MyIterator<>();
        forEach(entry -> {
            myIterator.elements.add(entry);
        });
        return myIterator;
    }

    /**
     * Método responsável por percorrer por entradas contidos na árvore.
     * @param entry Refere-se as entradas da árvore detentoras de valores e chaves.
     */
    @Override
    public void forEach(final Consumer<? super Entry<K, V>> entry) {
        forEach(entry, root);
    }

    /**
     * Método responsável por percorrer por entradas contidos na árvore recursivamente.
     * @param entry Refere-se as entradas da árvore detentoras de valores e chaves.
     * @param node  Refere-se ao elo atual da recursão.
     */
    private void forEach(final Consumer<? super Entry<K, V>> entry, final Node node) {
        if (node != null) {
            forEach(entry, node.left);
            entry.accept(node);
            forEach(entry, node.right);
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
     * @return Retorna elo raiz da árvore reconstruída com altura ajustada.
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
     * Método responsável por alterar os dados da árvore em fluxo.
     * @param newData Refere-se aos novos dados da árvore.
     */
    @Override
    public void set(final AVL<K, V> newData) {
        this.comparator = newData.comparator;
        this.root = newData.root;
        this.size = newData.size;
    }

    /**
     * Classe responsável por comportar-se como elo da árvore.
     * @author Everton Bruno Silva dos Santos.
     * @version 1.0
     */
    private class Node implements Entry<K, V>, Serializable, Duplicable<Node> {
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
        private transient int balancing;
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

        /**
         * Método responsável por alterar valor contido no elo.
         * @param value Refere-se ao novo valor contido no elo.
         * @return Retorna antigo valor contido no elo.
         */
        @Override
        public V setValue(final V value) {
            final V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        /**
         * Método responsável por duplicar o elo.
         * @return Retorna elo duplicata.
         */
        @Override
        public Node duplicate() {
            final Node node = new Node(key, value);
            if (leftIsNotNull()) {
                node.left = left.duplicate();
            }
            if (rightIsNotNull()) {
                node.right = right.duplicate();
            }
            return node;
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
            return !elements.isEmpty() ? elements.removeFirst() : null;
        }

    }

}
