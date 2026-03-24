 	/**
     * Friend class stores information about a friend including their name, gift, and price.
     */
public class Friend {
   private  String name;
   private String gift;
    private double price;

    public Friend(String name, String gift, double price) {
        this.name = name;
        this.gift = gift;
        this.price = price;
    }

    /**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the gift
	 */
	public String getGift() {
		return gift;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	@Override
    public String toString() {
        return name + " wants " + gift + " ($" + price + ")";
    }
}