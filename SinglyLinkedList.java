package cadastro;

public class SinglyLinkedList<E> implements List<E> {
	protected Node<E> head;
	protected Node<E> tail;
	protected int numElements;

	public SinglyLinkedList() {
		head = tail = null;
		numElements = 0;
	}

	public int numElements() {
		return numElements;
	}

	public boolean isEmpty() {
		return numElements == 0;
	}

	public boolean isFull() {
		// uma lista com alocaÃ§Ã£o dinÃ¢mica nunca estarÃ¡ cheia!
		return false;
	}

	public void insertFirst(E element) {
		// cria um novo nÃ³ e o torna o novo "head"
		Node<E> newNode = new Node<E>(element);
		if (isEmpty())
			head = tail = newNode;
		else {
			newNode.setNext(head);
			head = newNode;
		}
		numElements++;
	}

	public void insertLast(E element) {
		// cria um novo nÃ³ e o torna o novo "tail"
		Node<E> newNode = new Node<E>(element);
		if (isEmpty())
			head = tail = newNode;
		else {
			tail.setNext(newNode);
			tail = newNode;
		}
		numElements++;
	}

	public E removeFirst() {
		// verifica se hÃ¡ pelo menos um elemento a ser removido
		if (isEmpty())
			throw new UnderflowException();

		// guarda referÃªncia temporÃ¡ria ao elemento sendo removido
		E element = head.getElement();

		// se a lista possui somente 1 elemento, basta definir
		// "head" e "tail" para null...
		if (head == tail)
			head = tail = null;
		else
			// ...senÃ£o, o segundo elemento passa a ser o "head"
			head = head.getNext();

		numElements--;
		return element;
	}

	public E removeLast() {
		if (isEmpty())
			throw new UnderflowException();
		// guarda uma referÃªncia temporÃ¡ria ao elemento sendo removido
		E element = tail.getElement();
		// se a lista possui somente 1 elemento, basta definir
		// "head" e "tail" para null...
		if (head == tail)
			head = tail = null;
		else {
			// ...senÃ£o, Ã© necessÃ¡rio percorrer o encadeamento
			// atÃ© chegar ao nÃ³ imediatamente anterior ao Ãºltimo...
			Node<E> prev = head;
			while (prev.getNext() != tail)
				prev = prev.getNext();

			// ...para poder tornÃ¡-lo o novo "tail"
			tail = prev;
			prev.setNext(null);
		}
		numElements--;
		return element;
	}

	public void insert(E element, int pos) {
		// verifica se a posiÃ§Ã£o Ã© vÃ¡lida
		if (pos < 0 || pos > numElements)
			throw new IndexOutOfBoundsException();

		// casos especiais: inserÃ§Ã£o no inÃ­cio...
		if (pos == 0)
			insertFirst(element);
		else if (pos == numElements) // ... ou inserÃ§Ã£o no final
			insertLast(element);
		else { // caso geral: inserÃ§Ã£o no meio da lista
			// localiza o nÃ³ imediatamente anterior Ã  posiÃ§Ã£o
			// onde o novo serÃ¡ inserido
			Node<E> prev = head;
			for (int i = 0; i < pos - 1; i++)
				prev = prev.getNext();
			// cria um novo nÃ³ e o posiciona logo apÃ³s "prev",
			// ajustando os apontamentos e o total de elementos
			Node<E> newNode = new Node<E>(element);
			newNode.setNext(prev.getNext());
			prev.setNext(newNode);
			numElements++;
		}
	}

	public E remove(int pos) {
		if (pos < 0 || pos >= numElements)
			throw new IndexOutOfBoundsException();

		// casos especiais: remoÃ§Ã£o do inÃ­cio...
		if (pos == 0)
			return removeFirst();
		else if (pos == numElements - 1) // ... ou remoÃ§Ã£o do final
			return removeLast();
		else { // caso geral: remoÃ§Ã£o do meio da lista
			// localiza o nÃ³ imediatamente anterior Ã  posiÃ§Ã£o
			// de onde o elemento serÃ¡ removido
			Node<E> prev = head;
			for (int i = 0; i < pos - 1; i++)
				prev = prev.getNext();
			// guarda uma ref. temporÃ¡ria ao elemento sendo removido
			E element = prev.getNext().getElement();
			// ajusta o encadeamento "pulando" o nÃ³ sendo removido
			prev.setNext(prev.getNext().getNext());
			// ajusta o total de elementos e retorna o removido
			numElements--;
			return element;
		}
	}

	public int search(E element) {
		// percorre o encadeamento atÃ© encontrar o elemento
		Node<E> current = head;
		int i = 0;
		while (current != null) {
			if (element.equals(current.getElement()))
				return i;
			i++;
			current = current.getNext();
		}

		// se chegar atÃ© aqui, Ã© porque nÃ£o encontrou
		return -1;
	}

	public E get(int pos) {
		// verifica se a posiÂ�â€¹o Å½ vâ€¡lida
		if (pos < 0 || pos >= numElements)
			throw new IndexOutOfBoundsException();

		// percorre o encadeamento atÅ½ chegar ao elemento
		Node<E> current = head;
		for (int i = 0; i < pos; i++)
			current = current.getNext();

		return current.getElement();
	}

	public String toString() {
		String s = "";

		Node<E> current = head;
		while (current != null) {
			s += current.getElement().toString() + " ";
			current = current.getNext();
		}
		return s;
	}
}
