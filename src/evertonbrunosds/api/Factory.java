/*
 * This file is part of the SDSJavaLibs Open Source Project.
 * SDSJavaLibs is licensed under the GNU GPLv3.
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
package evertonbrunosds.api;

import java.util.concurrent.Semaphore;

/**
 * Classe responsável por construir instâncias de objetos.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 */
public interface Factory {

    /**
     * Classe responsável por fornecer métodos de instanciamento de Threads.
     * @author Everton Bruno Silva dos Santos.
     * @version 1.0
     */
    public static final class Thread {
        /**
         * Refere-se a instância singular de semáforo de thread.
         */
        public static final Semaphore SEMAPHORE = new Semaphore(1);

        /**
         * Método responsável por gerar instância de thread sem uso de semáforos.
         * @param worker Refere-se ao trabalhador que desempenhará dado trabalho.
         * @return Retorna instância de thread sem uso de semáforos.
         */
        public static java.lang.Thread makeFree(final Worker worker) {
            return new java.lang.Thread() {
                @Override
                public void run() {
                    worker.work();
                }
            };
        }

        /**
         * Método responsável por gerar instância de thread com uso de semáforo singular
         * compartilhado por todas as demais threads geradas por esse método.
         * @param worker Refere-se ao trabalhador que desempenhará dado trabalho.
         * @return Retorna instância de thread com uso de semáforo singular
         *         compartilhado por todas as demais threads geradas por esse método.
         */
        public static java.lang.Thread makeSafe(final Worker worker) {
            return new java.lang.Thread() {
                @Override
                public void run() {
                    try {
                        SEMAPHORE.acquire();
                        worker.work();
                    } catch (final InterruptedException ex) {
                        makeSafe(worker).start();
                    } finally {
                        SEMAPHORE.release();
                    }
                }
            };
        }

        /**
         * Método responsável por gerar instância de thread com uso de múltiplos semáforos.
         * @param worker     Refere-se ao trabalhador que desempenhará dado trabalho.
         * @param semaphores Refere-se aos múltiplos semáforos.
         * @return Retorna instância de thread com uso de múltiplos semáforos.
         */
        public static java.lang.Thread makeSafe(final Worker worker, final Iterable<Semaphore> semaphores) {
            return new java.lang.Thread() {
                @Override
                public void run() {
                    try {
                        for (final Semaphore semaphore : semaphores) {
                            semaphore.acquire();
                        }
                        worker.work();
                    } catch (final InterruptedException ex) {
                        makeSafe(worker).start();
                    } finally {
                        for (final Semaphore semaphore : semaphores) {
                            semaphore.release();
                        }
                    }
                }
            };
        }

        /**
         * Método responsável por gerar instância de thread com uso de múltiplos semáforos.
         * @param worker     Refere-se ao trabalhador que desempenhará dado trabalho.
         * @param semaphores Refere-se aos múltiplos semáforos.
         * @return Retorna instância de thread com uso de múltiplos semáforos.
         */
        public static java.lang.Thread makeSafeThread(final Worker worker, final Semaphore[] semaphores) {
            return new java.lang.Thread() {
                @Override
                public void run() {
                    try {
                        for (final Semaphore semaphore : semaphores) {
                            semaphore.acquire();
                        }
                        worker.work();
                    } catch (final InterruptedException ex) {
                        makeSafe(worker).start();
                    } finally {
                        for (final Semaphore semaphore : semaphores) {
                            semaphore.release();
                        }
                    }
                }
            };
        }

    }

}
