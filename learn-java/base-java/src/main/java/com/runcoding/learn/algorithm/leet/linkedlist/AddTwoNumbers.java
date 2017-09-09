package com.runcoding.learn.algorithm.leet.linkedlist;


import com.alibaba.fastjson.JSON;
import com.runcoding.learn.algorithm.leet.ListNode;

/**
 You are given two non-empty linked lists representing two non-negative integers.
 The digits are stored in reverse order and each of their nodes contain a single digit.
 Add the two numbers and return it as a linked list.
 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

  Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
  Output: 7 -> 0 -> 8
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4,l3);
        ListNode l2 = new ListNode(2,l4);

        ListNode l24 = new ListNode(4);
        ListNode l26 = new ListNode(6,l24);
        ListNode l25 = new ListNode(5,l26);
        ListNode listNode = addTwoNumbers(l2, l25);
        System.out.println(JSON.toJSON(listNode));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        ListNode current1 = l1;
        ListNode current2 = l2;
        
        ListNode head = new ListNode(0);
        ListNode currentHead = head;
        
        int sum = 0;
        
        while(current1 != null || current2 != null) {
            
            sum /= 10;
            
            if(current1 != null) {
                
                sum += current1.val;
                current1 = current1.next;
                
            }
            
            if(current2 != null) {
                
                sum += current2.val;
                current2 = current2.next;
                
            }
            
            currentHead.next = new ListNode(sum % 10);
            currentHead = currentHead.next;
            
        }
        
        
        if(sum / 10 == 1) {
            
            currentHead.next = new ListNode(1);
            
        }
        
        return head.next;
        
    }

}