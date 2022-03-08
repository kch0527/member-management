package iducs.java.pim201812043.repository;

import iducs.java.pim201812043.model.Member;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MemberRepositoryImpl<T> implements MemberRepository<T> {
    public static long memberId = 1;
    public List<T> memberList = null;
    Member memberDTO = null;


    public MemberRepositoryImpl() {
        memberList = new ArrayList<T>();
    }

    @Override
    public int create(T member) {
        int ret = 0;
        try {
            memberList.add((T) member);
            ret = 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    @Override
    public T readById(T member) {
        T retMember = null;
        for (T m : memberList) {
            if (((Member) m).getId() == ((Member) member).getId()) {
                retMember = m;
                break;
            }
        }
        return retMember;
    }

    @Override
    public T readByEmail(T member) {
        T retMember = null;
        for (T m : memberList) {
            if (
                    ((Member) member).getEmail().equals(((Member) m).getEmail())
                            && ((Member) member).getPw().equals(((Member) m).getPw()) // 암호도 확인한다.
            ) {
                retMember = m;
                break;
            }
        }
        return retMember;
    }

    @Override
    public List<T> readList() {
        return getMemberList();
    }

    @Override
    public int update(T member) {
        int ret = 0;
        for (T m : memberList) {
            if (((Member) m).getId() == ((Member) member).getId()) {
                memberList.set(ret, member);
            }
            ret++;
        }
        return ret;

    }

    @Override
    public int delete(T member) {
        int ret = 0;
        Iterator iter = memberList.iterator();
        while (iter.hasNext()) {
            if (((Member) iter.next()).getId() == ((Member) member).getId()) {
                iter.remove();
                ret++;
            }
        }
        return ret;
    }

    @Override
    public List<T> getMemberList() {
        return memberList;
    }

    @Override
    public void setMemberList(List<T> memberList) {
        this.memberList = memberList;
    }

    @Override
    public List<T> readListByPhone(T member) {

        ArrayList<T> result = new ArrayList<>();

        boolean isSamePhoneNum;
        boolean isSameLastPhoneNum;
        boolean isLastPhoneSearchType = isLastPhoneSearchType(((Member) member).getPhone());

        for (T memberData : memberList) {

            if (isLastPhoneSearchType) {
                isSameLastPhoneNum = getLastPhoneNumber(((Member) member).getPhone()).equals(getLastPhoneNumber(((Member) memberData).getPhone()));
                if (isSameLastPhoneNum) {
                    result.add(memberData);
                }
            } else {

                isSamePhoneNum = ((Member) member).getPhone().equals(((Member) memberData).getPhone());
                if (isSamePhoneNum) {
                    result.add(memberData);
                }
            }
        }

        return result;
    }

    @Override
    public List<T> readListByName(String order) {

        ArrayList<T> memberDataList = new ArrayList<>(memberList);

        if (order.equals("asc")) {
            memberDataList.sort(new Ascending());
        } else if (order.equals("desc")) {
            memberDataList.sort(new Descending());
        }

        return memberDataList;
    }

    @Override
    public List<T> readListByPerPage(int page, int perPage) {

        ArrayList<T> memberListInPage = new ArrayList<>();

        int endIdx = page * perPage - 1;
        int startIdx = endIdx - (perPage - 1);
        int limitIdx = memberList.size() - 1;

        endIdx = Math.min(endIdx, limitIdx);

        for (int idx = startIdx; idx <= endIdx; idx++) {
            memberListInPage.add(memberList.get(idx));
        }

        return memberListInPage;
    }

    @Override
    public boolean isExistEmail(String email) {

        for (T m : memberList) {
            if (((Member) m).getEmail().equals(email)) {
                return true;
            }
        }

        return false;
    }


    public boolean isLastPhoneSearchType(String phoneNumber) {
        return phoneNumber.split("-").length <= 1;
    }

    public String getLastPhoneNumber(String phoneNumber) {

        String[] splitPhone = phoneNumber.split("-");

        if (splitPhone.length > 1) {
            return splitPhone[2];
        } else {
            return phoneNumber;
        }
    }

    class Ascending implements Comparator<T> {

        @Override
        public int compare(T o1, T o2) {
            return (((Member) o1)).getName().compareTo((((Member) o2)).getName());
        }

    }

    class Descending implements Comparator<T> {

        @Override
        public int compare(T o1, T o2) {
            return (((Member) o2)).getName().compareTo((((Member) o1)).getName());
        }

    }
}
