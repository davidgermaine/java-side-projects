package com.techelevator.SideProjects;

import org.junit.*;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KataPotterTest {
	
	KataPotter basket = new KataPotter();
	
	@Test
	public void test_null_basket_returns_0() {
		int[] bookArray = null;
		assertEquals(0, basket.getDiscount(bookArray), 0.0001);
	}
	
	@Test
	public void test_empty_basket_returns_0() {
		int[] bookArray = {0, 0, 0, 0, 0};
		assertEquals(0, basket.getDiscount(bookArray), 0.0001);
	}
	
	@Test
	public void test_single_item_basket_returns_8() {
		int[] bookArray = new int[] {1, 0, 0, 0, 0};
		assertEquals(8, basket.getDiscount(bookArray), 0.0001);
	}
	
	@Test
	public void test_1_unique_item_returns_8_times_number_of_item() {
		int[] bookArray = new int[] {0, 0, 10, 0, 0};
		assertEquals(80, basket.getDiscount(bookArray), 0.0001);
	}
	
	@Test
	public void test_array_1_1_2_2_0_returns_40_80() {
		int[] bookArray = new int[] {1, 1, 2, 2, 0};
		assertEquals(40.80, basket.getDiscount(bookArray), 0.0001);
	}
	
	@Test
	public void test_array_2_2_2_1_1_returns_51_20() {
		int[] bookArray = new int[] {2, 2, 2, 1, 1};
		assertEquals(51.20, basket.getDiscount(bookArray), 0.0001);
	}
	
	@Test
	public void test_array_4_4_4_2_2_returns_102_40() {
		int[] bookArray = new int[] {4, 4, 4, 2, 2};
		assertEquals(102.40, basket.getDiscount(bookArray), 0.0001);
	}
	
	@Test
	public void test_array_3_3_3_1_1_returns_72_80() {
		int[] bookArray = new int[] {3, 3, 3, 1, 1};
		assertEquals(72.80, basket.getDiscount(bookArray), 0.0001);
	}

}
