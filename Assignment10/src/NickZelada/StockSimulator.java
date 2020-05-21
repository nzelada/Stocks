package NickZelada;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class for simulating trading a single stock at varying prices.
 * @author Nick Zelada
 * @version 04/20/2019 I affirm that this program is entirely my own work and
 * no other person's work is included.
 */
public class StockSimulator {

    private Map<String, Queue<Block>> blocks;

    /**
     * Constructor.
     */
    public StockSimulator() {
        blocks = new TreeMap<String, Queue<Block>>(); // new tree map

    }

    /**
     * Handle a user buying a given quantity of stock at a given price.
     *
     * @param quantity how many to buy.
     * @param price the price to buy.
     */
    public void buy(String symbol, int quantity, int price) {
        Queue<Block> stock = new LinkedList<>(); // new queue

        Block newBlock = new Block(quantity, price); // new Block object

        if (blocks.containsKey(symbol)) { // checks if there is a stock symbol

            stock = blocks.get(symbol); // if does, it gets the symbol

            stock.add(newBlock); // then adds to the Block

        } else { // if no symbol
            stock.add(newBlock); // just add it to the block
        }

        blocks.put(symbol, stock); // then put into the TreeMap

    }

    /**
     * Handle a user selling a given quantity of stock at a given price.
     *
     * @param symbol the stock to sell
     * @param quantity how many to sell.
     * @param price the price to sell.
     */
    public void sell(String symbol, int quantity, int price) {
        Queue<Block> sellStock = blocks.get(symbol); // gets from queue

        Block sellBlock = sellStock.poll(); // returning the first item in queue

        sellBlock.sell(quantity); // substracts the shares from the Block

        sellStock.offer(sellBlock); // puts the selling stock to the queue

        double gain = (price * quantity) - (sellBlock.getPrice() * quantity);
        /* the selling stock price times the quantity 
         minues the original price times the quantity*/
        System.out.println("The gain is: " + gain); // print
    }

}
